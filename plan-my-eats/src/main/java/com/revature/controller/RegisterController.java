package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.repository.UserService;

//@RestController
//@RequestMapping("/Register")
public class RegisterController {

//	@Autowired
//	UserService service;
//	// POST
//		@RequestMapping(method=RequestMethod.POST,
//				consumes=MediaType.APPLICATION_JSON_VALUE,
//				produces=MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<User> add(@RequestBody String username){
//			 //could add server side validation
//			User u = service.findByUsername(username);
//			if(u==null) {
//				// good he did not exist before so he will be added 
//				return new ResponseEntity<User>(u, HttpStatus.OK);
//			}else {
//				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//			}
//		}
}
