package com.vlad.backend.services.impl;

import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository UserRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.UserRepository = userRepository;
    }

    @Override
    public UserDTO get(Long id_usr) {
        Optional<User> byId = UserRepository.findById(id_usr);
        if (byId.isPresent()) {
            User user = byId.get();
            UserDTO dto = new UserDTO();
            dto.setId_usr(user.getId_usr());
//            return new UserDTO(User.getId_usr(), User.getName());
            return dto;
        } else {
            return null;
        }
    }



    @Override
    public void delete(Long id_usr) {
        UserRepository.findById(id_usr).ifPresent(UserRepository::delete);
    }
}
