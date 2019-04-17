package com.vlad.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class OurRecipe {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Ingredient> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
