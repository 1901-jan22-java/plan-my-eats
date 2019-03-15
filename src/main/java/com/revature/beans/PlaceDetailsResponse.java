package com.revature.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailsResponse {

	@JsonProperty("results")
	private List<PlaceDetails> results;

	public List<PlaceDetails> getResult() {
		return results;
	}

	public List<String> namesList() {
		int count = 0;
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < results.size(); i++) {
			if (count == 0) {
				names.add(results.get(i).toString());
			}
			count++;
			if (count == 2) {
				count = 0;
			}
		}
		return names;
	}

	public List<String> AddressList() {
		int count = 0;
		List<String> address = new ArrayList<String>();
		for (int i = 0; i < results.size(); i++) {
			if (count == 1) {
				address.add(results.get(i).toString());
			}
			count++;
			if (count == 2) {
				count = 0;
			}
		}
		return address;
	}

	public List<String> PhotosList() {
		int count = 0;
		List<String> photo = new ArrayList<String>();

		for (int i = 0; i < results.size(); i++) {
			if (count == 2) {
				photo.add(results.get(i).getPhotos().get(i).getReference());
			}
			count++;
			if (count == 3) {
				count = 0;
			}

		}
		return photo;
	}

	public void setResult(List<PlaceDetails> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "PlaceDetailsResponse [results=" + results + "]";
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class PlaceDetails {

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

@JsonIgnoreProperties(ignoreUnknown = true)
class PlacePhoto {

	@JsonProperty("photo_reference")
	private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "PlacePhoto [reference=" + reference + "]";
	}

}
