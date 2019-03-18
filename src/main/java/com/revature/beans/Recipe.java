package com.revature.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String ingredients;

	@Column(name = "CALORIES")
	private int calories;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PME_RECIPE_TYPE", joinColumns = @JoinColumn(name = "RECIPE_ID"), inverseJoinColumns = @JoinColumn(name = "PREF_ID"))
	private List<Preference> types;

	public Recipe() {
		super();
	}

	public Recipe(String recipeName, String ingredients, int calories, List<Preference> types) {
		super();
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.calories = calories;
		this.types = types;
	}

	public Recipe(int recipeId, String recipeName, String ingredients, int calories, List<Preference> types) {
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

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public List<Preference> getTypes() {
		return types;
	}

	public void setTypes(List<Preference> types) {
		this.types = types;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((recipeName == null) ? 0 : recipeName.hashCode());
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
		return true;
	}

}
