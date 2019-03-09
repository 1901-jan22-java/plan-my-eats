package com.revature.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.revature.beans.User;
import com.revature.repository.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {


	@Autowired
	UserService service;
	// POST
	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> add(@RequestBody User user){
		//could add server side validation
		User u = service.findByUsername(user.getUsername());
		System.out.println(user + " THIS WAS THE USER!!");
		if(u==null) {
			service.saveUser(user);
			System.out.println(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<String> showInfo() {
		return new ResponseEntity<String>("This means something is at least working!", HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<User> addPreference(@RequestBody User user) {
		return null;
	}

}
