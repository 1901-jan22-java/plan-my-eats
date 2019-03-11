package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Recipe;
import com.revature.repositories.RecipeRepository;

@Service
@Transactional
public class RecipeService {

	@Autowired
	private RecipeRepository repo;

	public Recipe findRecipeById(int id) {
		return repo.findOne(id);
	}

	public List<Recipe> getByIngredient(String ingredient) {
		return repo.findRecipeByIngredient(ingredient);
	}

	public Recipe getRecipeByName(String name) {
		return repo.findRecipeByName(name);
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

	public List<Recipe> getRecipesByTypes(String types) {
		return repo.findRecipesByTypes(types);
	}
}
