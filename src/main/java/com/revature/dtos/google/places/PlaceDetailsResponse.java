package com.revature.dtos.google.places;

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

	public List<String> NamesList() {
		List<String> names = new ArrayList<String>();

		for (PlaceDetails p : results) {
			names.add(p.getName());
		}
		return names;
	}

	public List<String> VicinityList() {
		List<String> address = new ArrayList<String>();

		for (PlaceDetails p : results) {
			address.add(p.getVicinity());
		}
		return address;
	}

	public List<String> PhotosList() {
		List<String> photo = new ArrayList<String>();
		
		for (PlaceDetails p : results) {
			System.out.println(p.getPhotos());
			if(p.getPhotos().size() > 0) {
				photo.add(p.getPhotos().get(0).getReference());
			} else {
				photo.add("No photo");
			}
		}
		return photo;
	}

	public List<String> LatitudeList() {
		List<String> ll = new ArrayList<String>();
		for (PlaceDetails pd : results) {
			ll.add(pd.getGeometry().getLocation().getLatitude());
		}
		return ll;
	}

	public List<String> LongitudeList() {
		List<String> ll = new ArrayList<>();
		for (PlaceDetails pd : results) {
			ll.add(pd.getGeometry().getLocation().getLongitude());
		}
		return ll;
	}

	public void setResult(List<PlaceDetails> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "PlaceDetailsResponse [results=" + results + "]";
	}

}
