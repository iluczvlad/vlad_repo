package com.vlad.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Ingredient> ingredients;
    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
