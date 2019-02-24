package com.recipe.demo.services;

import com.recipe.demo.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
