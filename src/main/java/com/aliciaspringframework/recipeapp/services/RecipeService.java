package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.commands.RecipeCommand;
import com.aliciaspringframework.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Object findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long l);
    void deleteById(Long idToDelete);
}
