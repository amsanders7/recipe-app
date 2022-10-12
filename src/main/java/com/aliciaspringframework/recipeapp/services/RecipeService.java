package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.commands.RecipeCommand;
import com.aliciaspringframework.recipeapp.models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Object findById(Long aLong);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
