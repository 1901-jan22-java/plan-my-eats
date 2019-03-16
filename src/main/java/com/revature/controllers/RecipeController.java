package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.revature.services.RecipeService;

@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	private static final Log log = LogFactory.getLog(RecipeController.class);

	@Autowired
	RecipeService rs;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> getRecipes() {
		return new ResponseEntity<List<Recipe>>(rs.getAll(), HttpStatus.OK);
	}

	// What in the actual world is this thing... o.o
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/recipe")
	public ResponseEntity<RecipeDetailsResults> hitRecApi(@RequestBody User u) {
		Random rand = new Random();
		String[] filters = new String[2];
		double calories = 0;

		/*
		 * Men BMi = 66.4730 + (13.7516 x weight in kg) + (5.0033 x height in cm) -
		 * (6.7550 x age in years)
		 * 
		 * Women BMR = 655.0955 + (9.5634 x weight in kg) + (1.8496 x height in cm) -
		 * (4.6756 x age in years)
		 * 
		 */
		if (u.getGender().equalsIgnoreCase("male")) {
			calories = 66.4730d + (13.7516 * (u.getWeight() * 0.4539d)) + (5.0033d * (u.getHeight() * 2.54d))
					- (6.7550 * u.getAge());
			calories *= 1.2d;
		} else if (u.getGender().equalsIgnoreCase("female")) {
			calories = 655.0955d + (9.5634d * (u.getWeight() * 0.4539d)) + (1.8496d * (u.getHeight() * 2.54d))
					- (4.6756d * u.getAge());
			calories *= 1.2d;
		}
		if (u.getPreferences().size() > 3) {
			// Keep track of an index
			int firstIndex = -1;
			// Limit the number of tries the loop can go through
			int tries = u.getPreferences().size() * 2;
			// Try to get 2 filters
			for (int i = 0; i < 2; i++) {
				// If you run out of tries exit to not be stuck forever
				if (tries == 0)
					break;
				// Get a random index that hasn't been used before
				int index = rand.nextInt(u.getPreferences().size());
				if (index == firstIndex) {
					i--;
				} else {
					firstIndex = index;
					List<Preference> li = new ArrayList<Preference>(u.getPreferences());
					Preference pref = li.get(index);
					if (pref.getPrefId() > 19) {
						tries--;
						i--;
					} else {
						filters[i] = pref.getName();
					}
				}
			}
		} else {
			List<Preference> li = new ArrayList<Preference>(u.getPreferences());
			if (li.size() != 0) {
				if (li.get(0).getPrefId() < 20) {
					filters[0] = li.get(0).getName();
				}
				if (li.size() == 2 && li.get(1).getPrefId() < 20) {
					filters[1] = li.get(1).getName();
				}
			}
		}
		String apiUrl = "https://api.edamam.com/search?q="
				+ "&app_id=3ee293b7&app_key=5143a3f492a353eb02eac1fac6912dbc&from=0&to=3&calories="
				+ (int) (calories * 0.33d) + "&health=" + filters[0] + "+" + filters[1];
		log.info(apiUrl);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<RecipeDetailsResults> responseEntity = restTemplate.getForEntity(apiUrl,
					RecipeDetailsResults.class);
			RecipeDetailsResults recipes = responseEntity.getBody();
			List<Recipe> recp = new ArrayList<Recipe>();
			for (int i = 0; i < 20; i++) {
				Recipe r = new Recipe();
				r.setCalories(recipes.getCalories(i) / recipes.getServings(i));
				StringBuilder sb = new StringBuilder();
				for(String ingredient :recipes.getIngredients(i)) {
					sb.append(ingredient + "\n");
				}
				r.setIngredients(sb.toString().substring(0, sb.length()-2));
				r.setRecipeName(recipes.getName(i));
				recp.add(r);
			}

			if (responseEntity.getStatusCode().toString().equals("200")) {
				return new ResponseEntity<RecipeDetailsResults>(recipes, HttpStatus.OK);
			} else {
				return new ResponseEntity<RecipeDetailsResults>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("User: " + u, e);
		}
		return new ResponseEntity<RecipeDetailsResults>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(path = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Recipe>> pleaseWork(User u) {
		return rs.searchByUserPreference(u);
	}

}