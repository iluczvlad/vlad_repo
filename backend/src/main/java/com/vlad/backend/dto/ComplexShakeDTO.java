package com.vlad.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class ComplexShakeDTO {
    private List<IngredientDTO> liquids = new ArrayList<>();
    private List<IngredientDTO> solids = new ArrayList<>();
    private List<IngredientDTO> spices = new ArrayList<>();

    public List<IngredientDTO> getLiquids() {
        return liquids;
    }

    public void setLiquids(List<IngredientDTO> liquids) {
        this.liquids = liquids;
    }

    public List<IngredientDTO> getSolids() {
        return solids;
    }

    public void setSolids(List<IngredientDTO> solids) {
        this.solids = solids;
    }

    public List<IngredientDTO> getSpices() {
        return spices;
    }

    public void setSpices(List<IngredientDTO> spices) {
        this.spices = spices;
    }
}
