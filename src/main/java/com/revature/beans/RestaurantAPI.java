package com.revature.beans;

public class RestaurantAPI {

	private String results;

	public RestaurantAPI() {
	}

	public RestaurantAPI(String results) {
		this.results = results;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "RestaurantApi [results=" + results + "]";
	}

}
