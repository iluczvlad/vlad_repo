package com.vlad.backend.services;

import com.vlad.backend.dto.IngredientDTO;

import java.util.List;

public interface IngredientService {

    IngredientDTO get(Long id);
    void delete(Long id);
    List<IngredientDTO> findAll();
}
