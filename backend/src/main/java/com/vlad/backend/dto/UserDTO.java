package com.vlad.backend.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private Boolean diabetic;
    private Boolean lactoseIntolerant;
    private List<IngredientDTO> allergies;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, String name ) {
        this.id = id;
        this.name = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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

    public List<IngredientDTO> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<IngredientDTO> allergies) {
        this.allergies = allergies;
    }
}
