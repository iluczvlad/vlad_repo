package com.vlad.backend.model;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long kcal;
    private Boolean detox;

    @Enumerated(EnumType.STRING)
    private Temperature temp;
    private Boolean vegan;
    private Boolean hasLactose;
    private String portion;
    private Boolean sweetener;

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

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
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

    public Boolean getSweetener() {
        return sweetener;
    }

    public void setSweetener(Boolean sweetener) {
        this.sweetener = sweetener;
    }
}
