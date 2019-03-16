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