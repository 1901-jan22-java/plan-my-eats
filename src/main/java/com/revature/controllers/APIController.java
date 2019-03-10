package com.revature.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.PlaceDetailsResponse;

@CrossOrigin
@RestController
//@RequestMapping("/api")
public class APIController {

	private static Log log = LogFactory.getLog(APIController.class);

	@RequestMapping(path = "/placesapi", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

	/*
	 * TODO: Turn this into API request for recipes
	 */
//	@RequestMapping(path = "/recipesapi", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PlaceDetailsResponse> hitRecipeAPI(@RequestBody String params) {
//		String[] filters = params.split(";");
//		String apiUrl = "" + filters[0] + "" + filters[1] + "";
//		
//		log.info("API URL: " + apiUrl);
//
//		try {
//
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<RecipeDetailsResponse> responseEntity = restTemplate.getForEntity(apiUrl,
//					PlaceDetailsResponse.class);
//			PlaceDetailsResponse restaurants = responseEntity.getBody();
//
//			if (responseEntity.getStatusCode().toString().equals("200")) {
//				return new ResponseEntity<RecipeDetailsResponse>(restaurants, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<RecipeDetailsResponse>(HttpStatus.BAD_REQUEST);
//			}
//
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return new ResponseEntity<RecipeDetailsResponse>(HttpStatus.BAD_REQUEST);
//	}

}
