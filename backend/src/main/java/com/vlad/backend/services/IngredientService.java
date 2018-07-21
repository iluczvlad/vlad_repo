package com.vlad.backend.services;

import com.vlad.backend.dto.IngredientDTO;

public interface IngredientService {

    IngredientDTO get(Long id);
    void delete(Long id);
}
