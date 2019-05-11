package com.vlad.backend.services;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.Type;

import java.util.List;

public interface IngredientService {

    IngredientDTO get(Long id);
    List<IngredientDTO> get(Type type, String email);
    void delete(Long id);
    List<IngredientDTO> findAll();
    IngredientDTO toDto(Ingredient ingredient);
    Ingredient toEntity(IngredientDTO dto);

    void save(IngredientDTO dto);
}
