package com.datapoaws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datapoaws.dao.ItineraryDao;
import com.datapoaws.model.Itinerary;
import com.datapoaws.service.ItineraryService;

@RestController
@RequestMapping("/rest/itinerary")
public class ItineraryRest {
	
	@Autowired
	private ItineraryDao itineraryDao;
	
	@Autowired
	private ItineraryService itineraryService;
	
	@PostMapping()
	public void save(@RequestBody Itinerary itinerary) {
		itineraryDao.save(itinerary);
	}
	
	@GetMapping("/{id}")
	public Itinerary getById(@PathVariable("id") String id) {
		return itineraryService.getById(id);
	}
	
}
