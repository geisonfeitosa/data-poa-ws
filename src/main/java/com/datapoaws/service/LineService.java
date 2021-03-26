package com.datapoaws.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datapoaws.dao.LineDao;
import com.datapoaws.model.Line;

@Service
public class LineService {
	
	@Autowired
	private LineDao lineDao;
	
	@Autowired
	private ItineraryService itineraryService;

	public void getLineWs() {
		RestTemplate restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		ResponseEntity<Line[]> responseEntity = restTemplate.getForEntity(
				"http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o", Line[].class);
		List<Line> LineList = Arrays.asList(responseEntity.getBody());

		for (Line line : LineList) {
			Line lineDb = lineDao.getById(line.getId());
			if (lineDb != null) line.setUuid(lineDb.getUuid());
			lineDao.save(line);
			
			itineraryService.getItineraryWs(line);
		}
	}
}
