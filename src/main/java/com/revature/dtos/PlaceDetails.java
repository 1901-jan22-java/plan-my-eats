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
	private String address;

	@JsonProperty("photos")
	private List<PlacePhoto> photos = Collections.emptyList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PlacePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PlacePhoto> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "PlaceDetails [name=" + name + ", address=" + address + ", photos=" + photos + "]";
	}

}
