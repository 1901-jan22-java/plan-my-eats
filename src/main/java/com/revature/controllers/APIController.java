package com.revature.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.dtos.edamam.recipes.RecipeDetailsResults;
import com.revature.dtos.google.places.PlaceDetailsResponse;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class APIController {

	private static Logger log = Logger.getLogger(APIController.class);

	@RequestMapping(path = "/restaurant", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceDetailsResponse> hitPlacesAPI(@RequestBody String params) {

		String[] filters = params.split(";");
		String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=" + filters[0]
				+ "&radius=1500&type=restaurant&keyword=" + filters[1] + "&key=AIzaSyAv7FWb5nyCLZw9fxrpkaKLc3NS1BRGeXM";

		log.info("API URL: " + apiUrl);

		try {

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PlaceDetailsResponse> responseEntity = restTemplate.getForEntity(apiUrl,
					PlaceDetailsResponse.class);
			PlaceDetailsResponse restaurants = responseEntity.getBody();

			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<PlaceDetailsResponse>(restaurants, HttpStatus.OK);
			} else {
				return new ResponseEntity<PlaceDetailsResponse>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("", e);
		}

		return new ResponseEntity<PlaceDetailsResponse>(HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(path = "/recipe", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeDetailsResults> hitRecApi(@RequestBody String params) {

		String[] filters = params.split(";");
		String apiUrl = "https://api.edamam.com/search?q=" + filters[0]
				+ "&app_id=3ee293b7&app_key=5143a3f492a353eb02eac1fac6912dbc&from=0&to=3&calories=" + filters[1]
				+ "&health=" + filters[2];

		log.info("API URL: " + apiUrl);

		try {

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RecipeDetailsResults> responseEntity = restTemplate.getForEntity(apiUrl,
					RecipeDetailsResults.class);
			RecipeDetailsResults recipes = responseEntity.getBody();
			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<RecipeDetailsResults>(recipes, HttpStatus.OK);
			} else {
				return new ResponseEntity<RecipeDetailsResults>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("", e);
		}

		return new ResponseEntity<RecipeDetailsResults>(HttpStatus.BAD_REQUEST);

	}

}
