package com.aliciaspringframework.recipeapp.repositories;

import com.aliciaspringframework.recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
