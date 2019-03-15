package com.revature.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService services;

	// ill update the user using a put method and then just save
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User u = services.findByUsername(user.getUsername());
		if (u.getPreferences().isEmpty() || u.getRecipes().isEmpty() || u.getRestaurants().isEmpty()) {

			services.updateUser(u, user.getPreferences(), user.getRecipes(), user.getRestaurants(), u.getUserId());
			return new ResponseEntity<User>(u, HttpStatus.OK);
		} else {
			// alright i know this is redundant 
			
			services.updateUser(u, user.getPreferences(), user.getRecipes(), user.getRestaurants(), u.getUserId());
			return new ResponseEntity<User>(user, HttpStatus.OK);

		}
	}
}
