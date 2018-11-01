package com.vlad.backend.services;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.model.Ingredient;

import java.util.List;

public interface IngredientService {

    IngredientDTO get(Long id);
    void delete(Long id);
    List<IngredientDTO> findAll();
    IngredientDTO toDto(Ingredient ingredient);
}
