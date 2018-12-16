package com.vlad.backend.services.impl;

import com.vlad.backend.dto.FavoriteDTO;
import com.vlad.backend.model.Favorite;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.FavoriteRepository;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.FavoriteService;
import com.vlad.backend.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final IngredientService ingredientService;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository, IngredientService ingredientService) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public List<FavoriteDTO> list(String email){
        User user = this.userRepository.findByEmail(email);
        return favoriteRepository.findAllByUser(user).stream()
                .map(it -> {
                    FavoriteDTO favoriteDTO = new FavoriteDTO();
                    favoriteDTO.setId(it.getId());
                    favoriteDTO.setIngredients(it.getIngredients().stream()
                            .map(ingredientService::toDto)
                            .collect(Collectors.toList()));
                    return favoriteDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(FavoriteDTO dto) {
        User user = this.userRepository.findByEmail(dto.getUserEmail());
        Favorite favorite = new Favorite();
        favorite.setIngredients(dto.getIngredients().stream().map(it -> new Ingredient(it.getId())).collect(Collectors.toList()));
        favorite.setUser(user);
        favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }
}
