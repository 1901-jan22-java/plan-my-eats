package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Restaurant;
import com.revature.beans.User;
import com.revature.dtos.PlaceDetailsResponse;
import com.revature.services.RestaurantService;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	private static final Logger log = Logger.getLogger(RestaurantController.class);

	@Autowired
	RestaurantService rs;
	@Autowired
	UserService us;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getRestaurant() {
		return new ResponseEntity<List<Restaurant>>(rs.getRestaurant(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> hitResApi(@RequestBody String params) {
		String[] filters = params.split(";");
		String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=" + filters[0]
				+ "&radius=1500&type=restaurant&keyword=" + filters[1] + "&key=AIzaSyAv7FWb5nyCLZw9fxrpkaKLc3NS1BRGeXM";
		log.info(apiUrl);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PlaceDetailsResponse> responseEntity = restTemplate.getForEntity(apiUrl,
					PlaceDetailsResponse.class);
			PlaceDetailsResponse restaurants = responseEntity.getBody();

			List<Restaurant> rests = new ArrayList<Restaurant>();
			log.info(restaurants.getResult().toArray().length);
			for (int i = 0; i < restaurants.getResult().toArray().length; i++) {

				Restaurant r = new Restaurant();
				r.setName(restaurants.NamesList().get(i));
				r.setLocation(restaurants.VicinityList().get(i));
				r.setType(filters[1]);
				r.setLatitude(restaurants.LatitudeList().get(i));
				r.setLongitude(restaurants.LongitudeList().get(i));
				r.setImgRef(restaurants.PhotosList().get(i));
				rests.add(r);

			}

			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception theException) {
			theException.printStackTrace();
		}
		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateRestaurantHistory(@RequestBody User user) {
		us.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(path = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> pleaseWork() {
		return new ResponseEntity<List<Restaurant>>(rs.getRestaurant(), HttpStatus.OK);
	}
}
