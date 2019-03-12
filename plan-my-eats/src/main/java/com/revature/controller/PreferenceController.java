package com.revature.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Preferences;
import com.revature.beans.User;
import com.revature.service.PreferencesService;
import com.revature.service.UserService;

@RestController
@RequestMapping("/preference")
public class PreferenceController {

	@Autowired
	PreferencesService service;
	@Autowired
	UserService services;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preferences>> getPreferences() {
		return new ResponseEntity<List<Preferences>>(service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updatePreference(@RequestBody Set<Preferences> preferences,User user) {
		User u = services.findByUsername(user.getUsername());
		if(u.getPreferences().isEmpty()) {
			//User has an account but no info is available for some reason
			services.updatePreferences(preferences,u.getUserId());

			return new ResponseEntity<User>(u, HttpStatus.OK);
		}else {
			services.updatePreferences(preferences,u.getUserId());
			
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}

}