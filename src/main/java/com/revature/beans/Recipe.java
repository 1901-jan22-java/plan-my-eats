package com.revature.beans;

import java.util.List;

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
	@SequenceGenerator(name = "PME_REC_SEQ_GEN", sequenceName = "PME_REC_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PME_REC_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private int recipeId;

	@Column(name = "NAME")
	private String recipeName;
	
	@Column(name = "INGREDIENTS")
	private List<String> ingredients;

	@Column(name = "CALORIES")
	private int calories;

	@Column(name = "TYPES")
	private String types;

	public Recipe() {
		super();
	}

	public Recipe(String recipeName, List<String> ingredients, int calories, String types) {
		super();
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.calories = calories;
		this.types = types;
	}

	public Recipe(int recipeId, String recipeName, List<String> ingredients, int calories, String types) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.calories = calories;
		this.types = types;
	}


	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((recipeName == null) ? 0 : recipeName.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (calories != other.calories)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (recipeName == null) {
			if (other.recipeName != null)
				return false;
		} else if (!recipeName.equals(other.recipeName))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}

	
}
