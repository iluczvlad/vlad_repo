package com.vlad.backend.dto;

public class IngredientDTO {
    private Long id;
    private String name;
    private Long kcal;
    private Boolean detox;
    private String temp;
    private Boolean vegan;
    private Boolean hasLactose;
    private String portion;

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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
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
}
