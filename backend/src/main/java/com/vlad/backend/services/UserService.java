package com.vlad.backend.services;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.model.Ingredient;

import java.util.List;

public interface UserService {

    UserDTO get(Long id_usr);
    UserDTO getByEmail(String email);
    void save(UserDTO dto);
    void savePreferences(UserDTO dto);
    void addToShoppingList(Long userId, List<IngredientDTO> shplst);
    void deleteFromShoppingList(Long userId, Long id);
}
