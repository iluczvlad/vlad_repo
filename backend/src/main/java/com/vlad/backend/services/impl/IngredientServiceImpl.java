package com.vlad.backend.services.impl;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.repositories.IngredientRepository;
import com.vlad.backend.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDTO get(Long id) {
        Optional<Ingredient> byId = ingredientRepository.findById(id);
        if (byId.isPresent()) {
            Ingredient ingredient = byId.get();
            IngredientDTO dto = new IngredientDTO();
            dto.setId(ingredient.getId());
//            return new IngredientDTO(ingredient.getId(), ingredient.getName());
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        ingredientRepository.findById(id).ifPresent(ingredientRepository::delete);
    }
}
