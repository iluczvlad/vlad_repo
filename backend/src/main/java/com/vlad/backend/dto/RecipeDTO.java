package com.vlad.backend.dto;

import com.vlad.backend.model.Temperature;

public class RecipeDTO {
    private String userEmail;
    private Integer minKcal;
    private Integer maxKcal;
    private Boolean detox;
    private Temperature temp;
    private Boolean vegan;
    private Boolean sweetener;
    private Integer maxsol;
    private Integer maxliq;
    private Boolean lactose;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getMinKcal() {
        return minKcal;
    }

    public void setMinKcal(Integer minKcal) {
        this.minKcal = minKcal;
    }

    public Integer getMaxKcal() {
        return maxKcal;
    }

    public void setMaxKcal(Integer maxKcal) {
        this.maxKcal = maxKcal;
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

    public Boolean getSweetener() {
        return sweetener;
    }

    public void setSweetener(Boolean sweetener) {
        this.sweetener = sweetener;
    }

    public Integer getMaxsol() {
        return maxsol;
    }

    public void setMaxsol(Integer maxsol) {
        this.maxsol = maxsol;
    }

    public Integer getMaxliq() {
        return maxliq;
    }

    public void setMaxliq(Integer maxliq) {
        this.maxliq = maxliq;
    }

    public Boolean getLactose() {
        return lactose;
    }

    public void setLactose(Boolean lactose) {
        this.lactose = lactose;
    }
}
