package com.revature.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDetailsResults {

	@JsonProperty("hits")
	private List<RecipeResults> hits;

	public List<RecipeResults> getHits() {
		return hits;
	}

	public String getName(int i) {
		return hits.get(i).getRecipe().getName();
	}

	public int getCalories(int i) {
		return hits.get(i).getRecipe().getCalories();
	}

	public int getServings(int i) {
		return hits.get(i).getRecipe().getServings();
	}

	public List<String> getIngredients(int i) {
		return hits.get(i).getRecipe().getIngredients();
	}

	public void setHits(List<RecipeResults> hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "RecipeDetailsResults [hits=" + hits + "]";
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class RecipeResults {

	@JsonProperty("recipe")
	private RecipeDetails recipe;

	public RecipeDetails getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDetails recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "RecipeResults [recipe=" + recipe + "]";
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class RecipeDetails {

	@JsonProperty("label")
	private String name;

	@JsonProperty("ingredientLines")
	private List<String> ingredients;

	@JsonProperty("calories")
	private int calories;

	@JsonProperty("yield")
	private int servings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	@Override
	public String toString() {
		return "RecipeDetails [name=" + name + ", calories=" + calories + "]";
	}

}
