package com.revature.controllers;

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
import com.revature.services.ImpTokenService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService us;

	@Autowired
	private ImpTokenService ts;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> showInfo() {
		return new ResponseEntity<String>("This means something is at least working!", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> register(@RequestBody User user) {
		User u = us.findByUsername(user.getUsername());

		if (u != null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		user = us.saveUser(user);
		String token = ts.generateToken(user);
		user.setToken(token);
		
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}
