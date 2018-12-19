package com.vlad.backend.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private Boolean diabetic;
    private Boolean lactoseIntolerant;
    @ManyToMany
    private List<Ingredient> allergies;
    @ManyToMany
    private Set<Ingredient> shoppingList;


    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(Boolean diabetic) {
        this.diabetic = diabetic;
    }

    public Boolean getLactoseIntolerant() {
        return lactoseIntolerant;
    }

    public void setLactoseIntolerant(Boolean lactoseIntolerant) {
        this.lactoseIntolerant = lactoseIntolerant;
    }

    public List<Ingredient> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Ingredient> allergies) {
        this.allergies = allergies;
    }

    public Set<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
