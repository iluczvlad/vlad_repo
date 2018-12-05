package com.vlad.backend.dto;

import com.vlad.backend.model.Temperature;
import com.vlad.backend.model.Type;

public class IngredientDTO {
    private Long id;
    private String name;
    private Long kcal;
    private Boolean detox;
    private Boolean vegan;
    private Boolean hasLactose;
    private String portion;
    private Temperature temp;
    private Type type;
    private Boolean Sweetener;

    public IngredientDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKcal() {
        return kcal;
    }

    public void setKcal(Long kcal) {
        this.kcal = kcal;
    }

    public Boolean getDetox() {
        return detox;
    }

    public void setDetox(Boolean detox) {
        this.detox = detox;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(Boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getSweetener() {
        return Sweetener;
    }

    public void setSweetener(Boolean sweetener) {
        Sweetener = sweetener;
    }
}
