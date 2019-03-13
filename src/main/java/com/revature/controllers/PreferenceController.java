package com.revature.controllers;

import java.util.List;

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

	@Autowired
	PreferenceService ps;
	@Autowired
	UserService us;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Preference>> getPreferences() {
		return new ResponseEntity<List<Preference>>(ps.getAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updatePreference(@RequestBody User user) {
		User u = us.findByUsername(user.getUsername());
		if(u.getPreferences().isEmpty()) {
			//User has an account but no info is available for some reason
			System.out.println("THIS IS THE USER!: " + u + ", WE SENT THIS IN : " + user);
			us.updatePreferences(user.getPreferences(), u.getUserId());
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}else {
			us.updatePreferences(user.getPreferences(), u.getUserId());
			
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}

}