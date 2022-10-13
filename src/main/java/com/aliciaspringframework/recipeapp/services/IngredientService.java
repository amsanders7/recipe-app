package com.aliciaspringframework.recipeapp.services;

import com.aliciaspringframework.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
