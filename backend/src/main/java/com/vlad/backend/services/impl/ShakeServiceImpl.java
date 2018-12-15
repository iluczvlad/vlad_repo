package com.vlad.backend.services.impl;

import com.vlad.backend.dto.ComplexShakeDTO;
import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.dto.RecipeDTO;
import com.vlad.backend.dto.SimpleShakeDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.Temperature;
import com.vlad.backend.model.Type;
import com.vlad.backend.repositories.IngredientRepository;
import com.vlad.backend.services.IngredientService;
import com.vlad.backend.services.ShakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        Random random = new Random();
        List<Ingredient> liquids = all.stream()
                .filter(i -> i.getType().equals(Type.LIQUID))
                .collect(Collectors.toList());
        dto.setLiquid(ingredientService.toDto(liquids.get(random.nextInt(liquids.size()))));
        List<Ingredient> sweeteners = all.stream()
                .filter(Ingredient::getSweetener)
                .collect(Collectors.toList());
        dto.setSweetener(ingredientService.toDto(sweeteners.get(random.nextInt(sweeteners.size()))));
        List<Ingredient> solids = all.stream()
                .filter(i -> i.getType().equals(Type.SOLID))
                .collect(Collectors.toList());
        int i = random.nextInt(solids.size());
        dto.setSolid1(ingredientService.toDto(solids.get(i)));
        solids.remove(i);
        dto.setSolid2(ingredientService.toDto(solids.get(random.nextInt(solids.size()))));
        return dto;
    }

    @Override
    public ComplexShakeDTO generateComplexShake(RecipeDTO recipe) {
        ComplexShakeDTO dto = new ComplexShakeDTO();
        Long kCounter = 0L;
        List<Ingredient> all = ingredientRepository.findAll();
        Random random = new Random();
        if (recipe.getDetox()){
            all=all.stream().filter(Ingredient::getDetox).collect(Collectors.toList());
        }
        if (recipe.getVegan()){
            all=all.stream().filter(Ingredient::getVegan).collect(Collectors.toList());
        }
        if (recipe.getLactose() == false){
            all=all.stream().filter(it -> !it.getHasLactose()).collect(Collectors.toList());
        }
        if (recipe.getTemp().equals(Temperature.COLD)){
            List<Ingredient> colds = all.stream().filter(it -> it.getTemp().equals(Temperature.COLD)).collect(Collectors.toList());
            Ingredient cold = colds.get(random.nextInt(colds.size()));
            if (cold.getType().equals(Type.SOLID)){
                dto.getSolids().add(ingredientService.toDto(cold));
            } else {
                dto.getLiquids().add(ingredientService.toDto(cold));
            }
            all.remove(cold);
            kCounter += cold.getKcal();
        }else{
            all=all.stream().filter(it -> it.getTemp().equals(Temperature.NORMAL)).collect(Collectors.toList());
        }
        if (recipe.getSweetener()){
            //TODO diabetus
            List<Ingredient> sweeteners = all.stream()
                    .filter(Ingredient::getSweetener)
                    .collect(Collectors.toList());
            Ingredient sweetener = sweeteners.get(random.nextInt(sweeteners.size()));
            dto.getSpices().add(ingredientService.toDto(sweetener));
            all.remove(sweetener);
            kCounter += sweetener.getKcal();
        }

        if (dto.getLiquids().size()<recipe.getMaxliq()){
            List<Ingredient> liquids = all.stream()
                    .filter(i -> i.getType().equals(Type.LIQUID))
                    .collect(Collectors.toList());
            for (int i=dto.getLiquids().size(); i<recipe.getMaxliq(); i++) {
                Ingredient liquid = liquids.get(random.nextInt(liquids.size()));
                dto.getLiquids().add(ingredientService.toDto(liquid));
                all.remove(liquid);
                liquids.remove(liquid);
                kCounter += liquid.getKcal();
            }
        }

        if (dto.getSolids().size()<recipe.getMaxsol()){
            List<Ingredient> solids = all.stream()
                    .filter(i -> i.getType().equals(Type.SOLID))
                    .sorted((a,b)-> (int) (a.getKcal()-b.getKcal()))
                    .collect(Collectors.toList());
            for (int i=dto.getSolids().size(); i<recipe.getMaxsol(); i++) {
                Ingredient solid = solids.get(random.nextInt(solids.size()));
                dto.getSolids().add(ingredientService.toDto(solid));
                all.remove(solid);
                solids.remove(solid);
                kCounter += solid.getKcal();
            }
        }




        return dto;
    }
}
