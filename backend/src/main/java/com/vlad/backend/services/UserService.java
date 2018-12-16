package com.vlad.backend.services;

import com.vlad.backend.dto.UserDTO;

public interface UserService {

    UserDTO get(Long id_usr);
    UserDTO getByEmail(String email);
    void save(UserDTO dto);
    void savePreferences(UserDTO dto);
}
