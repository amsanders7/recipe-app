package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
