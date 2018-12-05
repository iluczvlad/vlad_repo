package com.vlad.backend.services;

import com.vlad.backend.dto.UserDTO;

public interface UserService {

    UserDTO get(Long id_usr);
    void save(UserDTO dto);
}
