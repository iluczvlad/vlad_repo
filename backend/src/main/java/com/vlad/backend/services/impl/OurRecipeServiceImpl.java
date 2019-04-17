package com.vlad.backend.services.impl;

import com.vlad.backend.dto.OurRecipeDTO;
import com.vlad.backend.model.OurRecipe;
import com.vlad.backend.repositories.IngredientRepository;
import com.vlad.backend.repositories.OurRecipeRepository;
import com.vlad.backend.services.IngredientService;
import com.vlad.backend.services.OurRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OurRecipeServiceImpl implements OurRecipeService {

    private final OurRecipeRepository ourRecipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;

    @Autowired
    public OurRecipeServiceImpl(OurRecipeRepository ourRecipeRepository, IngredientRepository ingredientRepository, IngredientService ingredientService) {
        this.ourRecipeRepository = ourRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public List<OurRecipeDTO> findAll() {
        return ourRecipeRepository.findAll().stream().map(it -> {
            OurRecipeDTO dto = new OurRecipeDTO();
            dto.setId(it.getId());
            dto.setIngredients(it.getIngredients().stream().map(ingredientService::toDto).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void save(List<Long> ingIds) {
        OurRecipe recipe = new OurRecipe();
        recipe.setIngredients(ingIds.stream().map(id -> ingredientRepository.findById(id).get()).collect(Collectors.toList()));
        ourRecipeRepository.save(recipe);
    }

    @Override
    public void delete(Long id) {
        ourRecipeRepository.deleteById(id);
    }
}
