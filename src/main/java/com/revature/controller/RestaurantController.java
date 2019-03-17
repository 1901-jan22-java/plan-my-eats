package com.revature.controller;

import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.revature.beans.PlaceDetailsResponse;
import com.revature.beans.Restaurant;
import com.revature.beans.User;
import com.revature.service.RestaurantService;
import com.revature.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	RestaurantService service;
	@Autowired
	UserService services;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> getRestaurant() {
		return new ResponseEntity<List<Restaurant>>(service.getRestaurant(), HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.TEXT_PLAIN_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Restaurant>> hitResApi(@RequestBody String params) {
		String[] filters = params.split(";");
		String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=" + filters[0] + "&radius=1500&type=restaurant&keyword=" + filters[1] + "&key=AIzaSyAv7FWb5nyCLZw9fxrpkaKLc3NS1BRGeXM";
		System.out.println(apiUrl);
		
		try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PlaceDetailsResponse> responseEntity = restTemplate.getForEntity(apiUrl, PlaceDetailsResponse.class);
            PlaceDetailsResponse restaurants = responseEntity.getBody();
          
            List<Restaurant> rests = new ArrayList<Restaurant>();
            System.out.print(restaurants.getResult().toArray().length);
            for(int i=0;i<restaurants.getResult().toArray().length;i++) {
            	
            	Restaurant r = new Restaurant();
            	r.setName(restaurants.namesList().get(i));
            	r.setType(filters[1]);
            	r.setLocation(restaurants.AddressList().get(i));
            	r.setImgRef(restaurants.PhotosList().get(i));
            	r.setLatlng(restaurants.LatLongList().get(i));
            	rests.add(r);
            	
            }
          
            if (responseEntity.getStatusCode().toString().equals("200")) {
                return new ResponseEntity<List<Restaurant>>(rests, HttpStatus.OK);
            } else {
            	return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception theException) {
            theException.printStackTrace();
        }
		return new ResponseEntity<List<Restaurant>>(HttpStatus.BAD_REQUEST);	
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateRestaurantHistory(@RequestBody User user) {
		services.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}





