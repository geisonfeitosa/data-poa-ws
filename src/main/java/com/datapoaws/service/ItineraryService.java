package com.datapoaws.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datapoaws.dao.ItineraryDao;
import com.datapoaws.dao.LineDao;
import com.datapoaws.model.Itinerary;
import com.datapoaws.model.Line;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItineraryService {

	@Autowired
	private ItineraryDao itineraryDao;
	
	@Autowired
	private LineDao lineDao;


	public Itinerary getItineraryWs(Line line) {
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + line.getId(), Object.class);

		ObjectMapper m = new ObjectMapper();
		Map<String, Object> mappedObject = m.convertValue(responseEntity.getBody(),
				new TypeReference<Map<String, Object>>() {
				});

		if (mappedObject.get("idlinha") != null) {
			mappedObject.remove("idlinha");
			mappedObject.remove("nome");
			mappedObject.remove("codigo");

			Itinerary itinerary = itineraryDao.getByLine(line.getId());
			if (itinerary == null) {
				itinerary = new Itinerary();
				itinerary.setLine(line);
			}
			itinerary.setGeoJson(mappedObject);
			return itineraryDao.save(itinerary);
		}
		return null;
	}

	public Itinerary getById(String id) {
		Itinerary itinerary = itineraryDao.getByLine(id);
		if (itinerary == null) {
			Line line = lineDao.getById(id);
			if (line != null) itinerary = getItineraryWs(line);
		}
		return itinerary;
	}

}
