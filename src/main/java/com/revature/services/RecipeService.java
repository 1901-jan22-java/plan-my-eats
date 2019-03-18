package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.User;
import com.revature.configurations.KeyConfiguration;
import com.revature.dtos.edamam.recipes.RecipeDetails;
import com.revature.dtos.edamam.recipes.RecipeDetailsResults;
import com.revature.dtos.edamam.recipes.RecipeResults;
import com.revature.repositories.RecipeRepository;

@Service
public class RecipeService {

	private static final Logger log = Logger.getLogger(RecipeService.class);

	private static String appId = KeyConfiguration.PROPS.getProperty("edamam.recipe.appId");
	private static String appKey = KeyConfiguration.PROPS.getProperty("edamam.recipe.appKey");

	@Autowired
	private static RecipeRepository repo;

	public ResponseEntity<List<Recipe>> searchByUserDetails(User u) {
		// Default calories for a meal
		double calories = 500;
		final int prefLimit = 2;

		if (u.getGender().equalsIgnoreCase("male")) {
			calories = 66.4730d + (13.7516 * (u.getWeight() * 0.4539d)) + (5.0033d * (u.getHeight() * 2.54d))
					- (6.7550 * u.getAge());
			calories *= 1.2d;
		} else if (u.getGender().equalsIgnoreCase("female")) {
			calories = 655.0955d + (9.5634d * (u.getWeight() * 0.4539d)) + (1.8496d * (u.getHeight() * 2.54d))
					- (4.6756d * u.getAge());
			calories *= 1.2d;
		}

		List<Preference> prefs = u.getPreferences();
		List<String> healths = new ArrayList<>();
		Collections.shuffle(prefs);
		for (int i = 0, count = 0; count < prefLimit && i < prefs.size(); i++) {
			Preference p = prefs.get(i);
			if(p.getType().equalsIgnoreCase("health")) {
				healths.add(p.getName());
				count++;
			}
		}

		RestTemplate rt = new RestTemplate();
		ResponseEntity<RecipeDetailsResults> re = rt.getForEntity(buildAPIUrl(calories, healths),
				RecipeDetailsResults.class);

		return mapAndCacheEdamamAPI(re, healths);
	}

	public List<Recipe> getAll() {
		return repo.findAll();
	}

	/***********************************/
	/****** Find recipe by fields ******/
	/***********************************/

	public Recipe findById(int id) {
		return repo.findOne(id);
	}

	public List<Recipe> findByIngredients(String ingredients) {
		return repo.findRecipeByIngredients(ingredients);
	}

	public Recipe findRecipeByName(String name) {
		return repo.findRecipeByRecipeName(name);
	}

	public List<Recipe> findByCalories(int calories) {
		return repo.findRecipeByCalorie(calories);
	}

	public List<Recipe> findByLessThanCalorie(int calories) {
		return repo.findRecipesLessThanCalories(calories);
	}

	public List<Recipe> findByGreaterThanCalorie(int calories) {
		return repo.findRecipesGreaterThanCalories(calories);
	}

//	public List<Recipe> getRecipesByTypes(String types) {
//		return repo.findRecipesByTypes(types);
//	}

	/************************************/
	/********** HELPER METHODS **********/
	/************************************/

	private static String buildAPIUrl(double calories, List<String> health) {
		StringBuilder sb = new StringBuilder();
		for(String s: health) {
			sb.append("&health=" + s);
		}
		
		String apiUrl = "https://api.edamam.com/search?q=&app_id=" + appId + "&app_key=" + appKey
				+ "&from=0&to=3&calories=" + (int) (calories * 0.33d) + sb.toString();
		log.info("Edamam API Url: " + apiUrl);

		return apiUrl;
	}

	private static ResponseEntity<List<Recipe>> mapAndCacheEdamamAPI(ResponseEntity<RecipeDetailsResults> re,
			List<String> keywords) {
		List<Recipe> res = new ArrayList<>();

		for (RecipeResults rr : re.getBody().getHits()) {
			RecipeDetails rd = rr.getRecipe();

			StringBuilder sb = new StringBuilder();
			for (String i : rd.getIngredients()) {
				sb.append(i + "\n");
			}
			
			List<Preference> words = new ArrayList<>();
			for(String s: keywords) {
				words.add(new Preference(s, "Cuisine"));
			}

			Recipe r = new Recipe(rd.getName(), sb.toString().substring(0, sb.length() - 2),
					rd.getCalories(), words);
			repo.save(r);
			res.add(r);
		}

		return new ResponseEntity<List<Recipe>>(res, re.getStatusCode());
	}

}
