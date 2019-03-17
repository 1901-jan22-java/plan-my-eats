package com.revature.beans;

import org.springframework.stereotype.Controller;

@Controller
public class RestaurantApi {
	private String results;
	
	public RestaurantApi() {}

	public RestaurantApi(String results) {
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
