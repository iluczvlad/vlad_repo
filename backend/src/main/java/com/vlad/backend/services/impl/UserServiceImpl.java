package com.vlad.backend.services.impl;

import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.model.Ingredient;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.IngredientService;
import com.vlad.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    private final IngredientService ingredientService;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, PasswordEncoder passwordEncoder, IngredientService ingredientService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ingredientService = ingredientService;
    }

    @Override
    public UserDTO get(Long id_usr) {
        Optional<User> byId = userRepository.findById(id_usr);
        if (byId.isPresent()) {
            User user = byId.get();
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setName(user.getName());
            dto.setPassword(user.getPassword());

//            return new UserDTO(User.getId(), User.getName());
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setDiabetic(user.getDiabetic());
        dto.setLactoseIntolerant((user.getLactoseIntolerant()));
        dto.setAllergies(user.getAllergies().stream().map(ingredientService::toDto).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public void save(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void savePreferences(UserDTO dto) {
        User user = userRepository.findById(dto.getId()).get();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setAllergies(dto.getAllergies().stream().map(it -> new Ingredient(it.getId())).collect(Collectors.toList()));
        user.setDiabetic(dto.getDiabetic());
        user.setLactoseIntolerant(dto.getLactoseIntolerant());
        userRepository.save(user);
    }
}
