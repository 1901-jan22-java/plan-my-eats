package com.revature.controller;


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
import com.revature.interceptors.ImpTokenService;
import com.revature.interceptors.TokenService;
import com.revature.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService service;
	
	TokenService tokenService = ImpTokenService.getInstance();
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user) {
		User u = service.findByUsername(user.getUsername());
		if(u == null || !u.getPassword().equals(user.getPassword())) {
			//Username does not exist or password is incorrect
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT); 
		} else  {
			String token = tokenService.generateToken(u);
			u.setToken(token);
			
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	

}
