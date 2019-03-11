package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "PME_RECIPES")
@Component
public class Recipe {

	@Id
	@Column(name = "RECIPE_ID")
	@SequenceGenerator(name = "REC_SEQ_GEN", sequenceName = "REC_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "REC_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int recipeId;

	@Column(name = "INGREDIENTS")
	private String ingredients;

	@Column(name = "NAME")
	private String recipeName;

	@Column(name = "CALORIES")
	private int calories;

	@Column(name = "TYPES")
	private String types;

	public Recipe() {
	}

	public Recipe(String ingredients, String recipeName, int calories, String types) {
		this.ingredients = ingredients;
		this.recipeName = recipeName;
		this.calories = calories;
		this.types = types;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", ingredients=" + ingredients + ", recipeName=" + recipeName
				+ ", calories=" + calories + ", types=" + types + "]";
	}

}
