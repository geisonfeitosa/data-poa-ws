package com.datapoaws.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Itinerary {
	
	@Id
	private String uuid = UUID.randomUUID().toString();
	
	@OneToOne
	private Line line;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private Map<String, Object> geoJson = new HashMap<String, Object>();
	

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Map<String, Object> getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(Map<String, Object> geoJson) {
		this.geoJson = geoJson;
	}

	public String getUuid() {
		return uuid;
	}
	
}
