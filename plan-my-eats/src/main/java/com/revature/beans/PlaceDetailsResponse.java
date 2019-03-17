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
		List<String> names = new ArrayList<String>();
		
		for(PlaceDetails p : results) {
			names.add(p.getName());
		}
		return names;
	}

	public List<String> AddressList() {
		List<String> address = new ArrayList<String>();
		
		for(PlaceDetails p : results) {
			address.add(p.getAddress());
		}
		return address;
	}

	public List<String> PhotosList() {
		List<String> photo = new ArrayList<String>();
		
		for(PlaceDetails p : results) {
			if(p.getPhotos().size() > 0) {
				photo.add(p.getPhotos().get(0).getReference());
			} else {
				photo.add("No Photo");
			}
		}
		return photo;
	}

	public List<String> LatLongList() {
		List<String> latLong = new ArrayList<String>();
		for(PlaceDetails p : results) {
			latLong.add(p.getLocation().getLocation().getLatitude() + "," + p.getLocation().getLocation().getLongitude());
		}
		return latLong;
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

	@JsonProperty("geometry")
	private PlaceLocation location;

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

	public PlaceLocation getLocation() {
		return location;
	}

	public void setLocation(PlaceLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "PlaceDetails [name=" + name + ", address=" + address + ", photos=" + photos + "]";
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PlaceLocation {
	@JsonProperty("location")
	private LatLong location;

	public LatLong getLocation() {
		return location;
	}

	public void setLocation(LatLong location) {
		this.location = location;
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class LatLong {
	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("lng")
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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