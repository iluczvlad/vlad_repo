package com.vlad.backend.services.impl;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.Type;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.IngredientRepository;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Autowired
    public IngredientServiceImpl(final IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IngredientDTO get(Long id) {
        Optional<Ingredient> byId = ingredientRepository.findById(id);
        if (byId.isPresent()) {
            Ingredient ingredient = byId.get();
            return toDto(ingredient);
        } else {
            return null;
        }
    }

    @Override
    public List<IngredientDTO> get(Type type, String email) {
        if (Type.SPICE.equals(type)) {
            List<Ingredient> spices = ingredientRepository.findByType(Type.SPICED);
            User user = userRepository.findByEmail(email);
            if (!user.getDiabetic()) {
                spices.addAll(ingredientRepository.findByType(Type.SPICE));
            }
            return spices.stream().map(this::toDto).collect(Collectors.toList());
        } else {
            return ingredientRepository.findByType(type).stream().map(this::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<IngredientDTO> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDTO> dtos = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            dtos.add(toDto(ingredient));
        }
        return dtos;
    }

    @Override
    public void delete(Long id) {
        ingredientRepository.findById(id).ifPresent(ingredientRepository::delete);
    }

    @Override
    public IngredientDTO toDto(Ingredient ingredient) {
        IngredientDTO dto = new IngredientDTO();
        dto.setId(ingredient.getId());
        dto.setDetox(ingredient.getDetox());
        dto.setKcal(ingredient.getKcal());
        dto.setName(ingredient.getName());
        dto.setHasLactose(ingredient.getHasLactose());
        dto.setPortion(ingredient.getPortion());
        dto.setTemp(ingredient.getTemp());
        dto.setVegan(ingredient.getVegan());
        dto.setType(ingredient.getType());
        dto.setSweetener(ingredient.getSweetener());
        return dto;
    }
}
