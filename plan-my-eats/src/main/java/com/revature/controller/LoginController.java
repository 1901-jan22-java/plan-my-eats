package com.revature.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.repository.UserService;



@RestController
@RequestMapping("/Login")
public class LoginController {

	@Autowired
	UserService service;
	
	// find user by user name and then save to the session
	@RequestMapping(value="/{username}",
	    method=RequestMethod.GET,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		
		// I guess this is all i need but how do we maintain session ? with this user ?? to be revealed later 
		User u = service.findByUsername(username);
		
		if(u==null) {
			//user is null, return null/no resp body with a Http status of no content
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			
		}
		else {
			//all good, return user w status of ok
			return new ResponseEntity<User>(u, HttpStatus.OK);
			
		}
	}

}
