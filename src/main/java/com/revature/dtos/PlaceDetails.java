package com.revature.dtos;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetails {

	@JsonProperty("name")
	private String name;

	@JsonProperty("vicinity")
	private String vicinity;

	@JsonProperty("photos")
	private List<PlacePhoto> photos = Collections.emptyList();

	@JsonProperty("geometry")
	private PlaceGeometry geometry;

	public PlaceDetails() {
		super();
	}

	public PlaceDetails(String name, String vicinity, List<PlacePhoto> photos, PlaceGeometry geometry) {
		super();
		this.name = name;
		this.vicinity = vicinity;
		this.photos = photos;
		this.geometry = geometry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public List<PlacePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PlacePhoto> photos) {
		this.photos = photos;
	}

	public PlaceGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(PlaceGeometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "PlaceDetails [name=" + name + ", vicinity=" + vicinity + ", photos=" + photos + ", geometry=" + geometry
				+ "]";
	}

}