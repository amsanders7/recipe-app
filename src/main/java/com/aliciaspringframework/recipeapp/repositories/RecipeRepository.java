package com.aliciaspringframework.recipeapp.repositories;

import com.aliciaspringframework.recipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
