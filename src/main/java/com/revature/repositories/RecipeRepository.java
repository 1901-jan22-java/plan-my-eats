package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import com.revature.beans.Recipe;

@Transactional
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

	// get by ingred, name, calories types
	@Query("Select r from Recipe r WHERE r.ingredients = ?1")
	List<Recipe> findRecipeByIngredients(String ingredients);

	@Query("Select r from Recipe r WHERE r.recipeName = ?1")
	Recipe findRecipeByRecipeName(String name);

	@Query("Select r from Recipe r WHERE r.calories = ?1")
	List<Recipe> findRecipeByCalorie(int calories);

	@Query("Select r from Recipe r WHERE r.calories < ?1")
	List<Recipe> findRecipesLessThanCalories(int calories);

	@Query("Select r from Recipe r WHERE r.calories > ?1")
	List<Recipe> findRecipesGreaterThanCalories(int calories);

}
