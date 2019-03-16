package com.revature.dtos;

import java.util.ArrayList;
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
