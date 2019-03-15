package com.revature.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.Restaurant;
import com.revature.beans.User;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/testing")
public class TestController {

	private static final Log log = LogFactory.getLog(TestController.class);

	@Autowired
	UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public String someGetRequest() {
		return "Success!";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> apiRequest(@RequestBody String apiUrl) {
		log.info(apiUrl);
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception theException) {
			theException.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody UserPrefs prefs) {
		User user = prefs.user;
		Restaurant res = prefs.restaurants;
		Recipe rec = prefs.recipes;
		Preference pre = prefs.preferences;

		User u = service.findByUsername(user.getUsername());

		if (u == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			u.getRestaurants().add(res);
			u.getRecipes().add(rec);
			u.getPreferences().add(pre);

			// Update user
//			service.update(u);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}

	}

	static class UserPrefs {

		public User user;
		public Restaurant restaurants;
		public Recipe recipes;
		public Preference preferences;

	}

}
