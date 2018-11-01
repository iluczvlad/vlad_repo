package com.vlad.backend.dto;

public class SimpleShakeDTO {
    private IngredientDTO liquid;
    private IngredientDTO sweetener;
    private IngredientDTO solid1;
    private IngredientDTO solid2;

    public IngredientDTO getLiquid() {
        return liquid;
    }

    public void setLiquid(IngredientDTO liquid) {
        this.liquid = liquid;
    }

    public IngredientDTO getSweetener() {
        return sweetener;
    }

    public void setSweetener(IngredientDTO sweetener) {
        this.sweetener = sweetener;
    }

    public IngredientDTO getSolid1() {
        return solid1;
    }

    public void setSolid1(IngredientDTO solid1) {
        this.solid1 = solid1;
    }

    public IngredientDTO getSolid2() {
        return solid2;
    }

    public void setSolid2(IngredientDTO solid2) {
        this.solid2 = solid2;
    }
}
