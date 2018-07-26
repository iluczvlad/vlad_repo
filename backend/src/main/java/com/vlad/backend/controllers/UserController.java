package com.vlad.backend.controllers;

import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @RequestMapping(value = "/{id_usr}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable Long id_usr) {
        return new ResponseEntity<>(userService.get(id_usr), HttpStatus.OK);
    }
}
