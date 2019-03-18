package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.ImpTokenService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService us;

	@Autowired
	private ImpTokenService ts;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findByUsername(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		User u = us.findByUsername(username);

		if (u == null || !u.getPassword().equals(password)) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		String token = ts.generateToken(u);
		u.setToken(token);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	// find user by user name and then save to the session
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user) {
		// I guess this is all i need but how do we maintain session ? with this user ??
		// to be revealed later
		User u = us.findByUsername(user.getUsername());
		if (u == null || !u.getPassword().equals(user.getPassword())) {
			// user is null, return null/no resp body with a Http status of no content
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		String token = ts.generateToken(u);
		u.setToken(token);

		// all good, return user w status of ok
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

}
