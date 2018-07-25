package com.vlad.backend.services;

import com.vlad.backend.dto.UserDTO;

public interface UserService {

    UserDTO get(Long id_usr);
    void delete(Long id_usr);
}
