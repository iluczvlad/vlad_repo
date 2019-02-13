package com.vlad.backend.repositories;

import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByType(Type type);
}
