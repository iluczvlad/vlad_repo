package com.vlad.backend.services.impl;

import com.vlad.backend.dto.SimpleShakeDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.Temperature;
import com.vlad.backend.repositories.IngredientRepository;
import com.vlad.backend.services.IngredientService;
import com.vlad.backend.services.ShakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShakeServiceImpl implements ShakeService {
    private final IngredientService ingredientService;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ShakeServiceImpl(IngredientService ingredientService,
                            IngredientRepository ingredientRepository) {
        this.ingredientService = ingredientService;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public SimpleShakeDTO generateSimpleShake() {
        SimpleShakeDTO dto = new SimpleShakeDTO();
        List<Ingredient> all = ingredientRepository.findAll();
        Ingredient k = all.stream().filter(ingredient -> ingredient.getTemp().equals(Temperature.COLD)).findFirst().get();
        dto.setLiquid(ingredientService.toDto(k));
        dto.setSolid1(ingredientService.toDto(k));
        dto.setSolid2(ingredientService.toDto(k));
        dto.setSweetener(ingredientService.toDto(k));
        return dto;
    }
}
