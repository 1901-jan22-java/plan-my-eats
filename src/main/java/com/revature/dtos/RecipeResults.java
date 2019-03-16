package com.revature.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResults {

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
