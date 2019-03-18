package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
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

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.User;
import com.revature.dtos.RecipeDetailsResults;
import com.revature.dtos.RecipeResults;
import com.revature.services.RecipeService;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	private static final Logger log = Logger.getLogger(RecipeController.class);

	@Autowired
	UserService us;
	@Autowired
	RecipeService rs;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> hitRecApi(@RequestBody User u) {
		
		List<String> filters = new ArrayList<String>();
		filters.add("pasta");
		filters.add("rice");
		filters.add("sauce");
		filters.add("cake");
		filters.add("pastry");
		filters.add("bread");
		filters.add("eggs");
		filters.add("noodle");
		filters.add("beans");
		
		int rand = (int) Math.floor(Math.random()*filters.size());
		double calories = 0;

		/*
		 * Men BMR = 66.4730 + (13.7516 x weight in kg) + (5.0033 x height in cm) -
		 * (6.7550 x age in years)
		 * 
		 * Women BMR = 655.0955 + (9.5634 x weight in kg) + (1.8496 x height in cm) -
		 * (4.6756 x age in years)
		 * 
		 */
		if (u.getGender().equalsIgnoreCase("male")) {
			calories = 66.4730d + (13.7516d * (u.getWeight() * 0.4539d)) + (5.0033d * (u.getHeight() * 2.54d))
					- (6.7550d * u.getAge());
			calories *= 1.2d;
		} else if (u.getGender().equalsIgnoreCase("female")) {
			calories = 655.0955d + (9.5634d * (u.getWeight() * 0.4539d)) + (1.8496d * (u.getHeight() * 2.54d))
					- (4.6756d * u.getAge());
			calories *= 1.2d;
		}
		String calorieCount = (int)((calories/3)-100) + "-" + (int)((calories/3)+100);
		
		int pRand = (int) Math.floor(Math.random()*u.getPreferences().size());
		int count = 0;
		String pref = "";
		for(Preference p : u.getPreferences()) {
			if(count == pRand) {
				pref = p.getName();
				break;
			}
			count++;
		}
//		if (u.getPreferences().size() > 3) {
//			// Keep track of an index
//			int firstIndex = -1;
//			// Limit the number of tries the loop can go through
//			int tries = u.getPreferences().size() * 2;
//			// Try to get 2 filters
//			for (int i = 0; i < 2; i++) {
//				// If you run out of tries exit to not be stuck forever
//				if (tries == 0)
//					break;
//				// Get a random index that hasn't been used before
//				int index = rand.nextInt(u.getPreferences().size());
//				if (index == firstIndex) {
//					i--;
//				} else {
//					firstIndex = index;
//					List<Preference> li = new ArrayList<Preference>(u.getPreferences());
//					Preference pref = li.get(index);
//					if (pref.getPrefId() > 19) {
//						tries--;
//						i--;
//					} else {
//						filters[i] = pref.getName();
//					}
//				}
//			}
//		} else {
//			List<Preference> li = new ArrayList<Preference>(u.getPreferences());
//			if (li.size() != 0) {
//				if (li.get(0).getPrefId() < 20) {
//					filters[0] = li.get(0).getName();
//				}
//				if (li.size() == 2 && li.get(1).getPrefId() < 20) {
//					filters[1] = li.get(1).getName();
//				}
//			}
//		}
		
		
		String apiUrl = "https://api.edamam.com/search?q=" + filters.get(rand) 
				+ "&app_id=3ee293b7&app_key=5143a3f492a353eb02eac1fac6912dbc&from=0&to=20&calories="
				+ calorieCount +"&health=" + pref;
		log.info(apiUrl);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RecipeDetailsResults> responseEntity = restTemplate.getForEntity(apiUrl,
					RecipeDetailsResults.class);
			RecipeDetailsResults recipes = responseEntity.getBody();

			List<Recipe> recp = new ArrayList<Recipe>();
			for (int i = 0; i < recipes.getHits().size(); i++) {
				Recipe r = new Recipe();
				r.setCalories(recipes.getCalories(i) / recipes.getServings(i));
				r.setIngredients(recipes.getIngredients(i).toString());
				r.setRecipeName(recipes.getName(i));
				r.setUrl(recipes.getUrl(i));
				recp.add(r);
			}
			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<List<Recipe>>(recp, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Recipe>>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("", e);
			apiUrl = "https://api.edamam.com/search?q=" + filters.get(rand) 
			+ "&app_id=3ee293b7&app_key=5143a3f492a353eb02eac1fac6912dbc&from=0&to=20&calories="
			+ calorieCount;
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RecipeDetailsResults> responseEntity = restTemplate.getForEntity(apiUrl,
					RecipeDetailsResults.class);
			RecipeDetailsResults recipes = responseEntity.getBody();

			List<Recipe> recp = new ArrayList<Recipe>();
			for (int i = 0; i < recipes.getHits().size(); i++) {
				Recipe r = new Recipe();
				r.setCalories(recipes.getCalories(i) / recipes.getServings(i));
				r.setIngredients(recipes.getIngredients(i).toString());
				r.setRecipeName(recipes.getName(i));
				r.setUrl(recipes.getUrl(i));
				recp.add(r);
			}
			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<List<Recipe>>(recp, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Recipe>>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateRestaurantHistory(@RequestBody User user) {
		us.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
