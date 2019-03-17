package com.revature.controllers;

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

import com.revature.beans.Preference;
import com.revature.beans.User;
import com.revature.services.PreferenceService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/preference")
public class PreferenceController {

	private static final Logger log = Logger.getLogger(PreferenceController.class);

	@Autowired
	PreferenceService ps;
	@Autowired
	UserService us;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preference>> getPreferences() {
		return new ResponseEntity<List<Preference>>(ps.getAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updatePreference(@RequestBody User u) {
		log.info("USER: " + u);
		// User u = us.findByUsername(user.getUsername());
		if (u.getPreferences().isEmpty()) {
			// us.updatePreferences(u, user.getPreferences(), u.getUserId());
			us.saveUser(u);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		} else {
			// us.updatePreferences(u, user.getPreferences(), u.getUserId());
			us.saveUser(u);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}

}