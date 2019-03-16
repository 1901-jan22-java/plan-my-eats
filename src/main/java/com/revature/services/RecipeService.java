package com.revature.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Preference;
import com.revature.beans.Recipe;
import com.revature.beans.User;
import com.revature.dtos.RecipeDetails;
import com.revature.dtos.RecipeDetailsResults;
import com.revature.dtos.RecipeResults;
import com.revature.repositories.RecipeRepository;

@Service
@Transactional
public class RecipeService {

	private static final Log log = LogFactory.getLog(RecipeService.class);

	@Autowired
	private RecipeRepository repo;

	public ResponseEntity<List<Recipe>> searchByUserPreference(User u) {
		double calories = 500;

		if (u.getGender().equalsIgnoreCase("male")) {
			calories = 66.4730d + (13.7516 * (u.getWeight() * 0.4539d)) + (5.0033d * (u.getHeight() * 2.54d))
					- (6.7550 * u.getAge());
			calories *= 1.2d;
		} else if (u.getGender().equalsIgnoreCase("female")) {
			calories = 655.0955d + (9.5634d * (u.getWeight() * 0.4539d)) + (1.8496d * (u.getHeight() * 2.54d))
					- (4.6756d * u.getAge());
			calories *= 1.2d;
		}

		final int prefLimit = 2;
		List<Preference> prefs = new ArrayList<>(u.getPreferences());
		Collections.shuffle(prefs);
		StringBuilder keywords = new StringBuilder();
		for (int i = 0; i < prefLimit && i < prefs.size(); i++) {
			keywords.append(prefs.get(i).getName() + "+");
		}

		keywords.setLength(Math.max(keywords.length() - 1, 0));

		String apiUrl = "https://api.edamam.com/search?q="
				+ "&app_id=3ee293b7&app_key=5143a3f492a353eb02eac1fac6912dbc&from=0&to=3&calories="
				+ (int) (calories * 0.33d) + "&health=" + keywords.toString();
		log.info("API Url: " + apiUrl);

		RestTemplate rt = new RestTemplate();
		ResponseEntity<RecipeDetailsResults> re = rt.getForEntity(apiUrl, RecipeDetailsResults.class);

		List<Recipe> res = new ArrayList<>();

		for (RecipeResults rr : re.getBody().getHits()) {
			RecipeDetails rd = rr.getRecipe();

			// Recipe Constructor Signature
			// public Recipe(String ingredients, String recipeName, int calories, String
			// types)
			StringBuilder sb = new StringBuilder();
			for (String i : rd.getIngredients()) {
				sb.append(i + "\n");
			}

			Recipe r = new Recipe(rd.getName(), sb.toString().substring(0, sb.length() - 2),
					(rd.getCalories() / rd.getServings()), keywords.toString());
			repo.save(r);
			res.add(r);
		}

		return new ResponseEntity<List<Recipe>>(res, re.getStatusCode());
	}

	public List<Recipe> getAll() {
		return repo.findAll();
	}

	public Recipe findRecipeById(int id) {
		return repo.findOne(id);
	}

	public List<Recipe> getByIngredients(String ingredients) {
		return repo.findRecipeByIngredients(ingredients);
	}

	public Recipe getRecipeByName(String name) {
		return repo.findRecipeByRecipeName(name);
	}

	public List<Recipe> getByCalories(int calories) {
		return repo.findRecipeByCalorie(calories);
	}

	public List<Recipe> getByLessThanCalorie(int calories) {
		return repo.findRecipesLessThanCalories(calories);
	}

	public List<Recipe> getByLGreaterThanCalorie(int calories) {
		return repo.findRecipesGreaterThanCalories(calories);
	}

//	public List<Recipe> getRecipesByTypes(String types) {
//		return repo.findRecipesByTypes(types);
//	}

}
