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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.User;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/testing")
public class TestController {
	
	@Autowired
	UserService us;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> someGetRequest() {
		System.out.println("The method is reached");
		return us.getAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.TEXT_PLAIN_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> apiRequest(@RequestBody String apiUrl) {
		System.out.println(apiUrl);
		 try {
	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
	            if (responseEntity.getStatusCode().toString().equals("200")) {
	                return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
	            } else {
	            	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	            }

	        } catch (Exception theException) {
	            theException.printStackTrace();
	            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	        }
	}
}