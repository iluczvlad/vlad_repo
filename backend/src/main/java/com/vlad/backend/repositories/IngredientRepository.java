package com.vlad.backend.repositories;

import com.vlad.backend.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
