package com.revature.controllers;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.Restaurant;
import com.revature.beans.User;
import com.revature.services.ImpTokenService;
import com.revature.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserService us;
	
	@Autowired
	private ImpTokenService ts;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAll() {
		log.info("ADMIN USER SERVICE");
		return new ResponseEntity<List<User>>(us.getAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable int id) {
		User u = us.findById(id);
		if (u == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}/preferences", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Preference>> getUserPreferences(@PathVariable int id) {
		User u = us.findById(id);
		if (u == null) {
			return new ResponseEntity<Collection<Preference>>(HttpStatus.NO_CONTENT);
		}
		Collection<Preference> ps = u.getPreferences();
		if (ps.isEmpty()) {
			return new ResponseEntity<Collection<Preference>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ps, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}/restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Restaurant>> getUserRestaurantHistory(@PathVariable int id) {
		User u = us.findById(id);
		if (u == null) {
			return new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
		}
		Collection<Restaurant> rs = u.getRestaurants();
		if (u == null || rs.isEmpty()) {
			return new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}/recipes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Recipe>> getUserRecipeHistory(@PathVariable int id) {
		User u = us.findById(id);
		if (u == null) {
			return new ResponseEntity<Collection<Recipe>>(HttpStatus.NO_CONTENT);
		}
		Collection<Recipe> rs = u.getRecipes();
		if (rs.isEmpty()) {
			return new ResponseEntity<Collection<Recipe>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	// find user by user name and then save to the session
	@RequestMapping(path = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		User u = us.findByUsername(username);

		if (u == null) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(us.saveUser(user), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

	@RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User user) {
		User u = us.findByUsername(user.getUsername());
		if (u == null || !u.getPassword().equals(user.getPassword())) {
			// Username does not exist or password is incorrect
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		String token = ts.generateToken(u);
		u.setToken(token);

		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
}
