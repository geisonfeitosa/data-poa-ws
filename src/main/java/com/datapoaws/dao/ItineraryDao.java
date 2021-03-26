package com.datapoaws.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.datapoaws.model.Itinerary;

@Repository
public interface ItineraryDao extends JpaRepository<Itinerary, String> {
	
	@Query("select i from Itinerary i where i.line.id = :id")
	Itinerary getByLine(String id);

}
