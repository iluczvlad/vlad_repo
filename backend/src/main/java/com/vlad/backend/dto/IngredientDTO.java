package com.vlad.backend.dto;

public class IngredientDTO {
    private Long id;
    private String name;
    private Long kcal;
    private String detox;
    private String temp;
    private String taste;
    private String unit;
    private String vegan;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String name, Long kcal, String detox, String temp, String taste, String unit, String vegan ) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.detox = detox;
        this.temp = temp;
        this.taste = taste;
        this.unit = unit;
        this.vegan = vegan;
    }

    public Long getId() { return id; }

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

    public String getDetox() { return detox; }

    public void setDetox(String detox) { this.detox = detox; }

    public String getTemp() { return temp; }

    public void setTemp(String temp) { this.temp = temp; }

    public String getTaste() { return taste; }

    public void setTaste(String taste) { this.taste = taste; }

    public String getUnit() { return unit; }

    public void setUnit(String unit) { this.unit = unit; }

    public String getVegan() { return vegan; }

    public void setVegan(String vegan) { this.vegan = vegan; }
}
