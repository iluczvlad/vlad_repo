package com.vlad.backend.controllers;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @RequestMapping(value = "/li/user/login", method = RequestMethod.GET)
    public ResponseEntity<Boolean> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/li/user", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> getByEmail(@RequestBody String email) {
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/user/{id_usr}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable Long id_usr) {
        return new ResponseEntity<>(userService.get(id_usr), HttpStatus.OK);
    }

    @RequestMapping(value = "/nl/user/save", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        userService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/li/user/prefs", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> prefs(@RequestBody UserDTO dto) {
        userService.savePreferences(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/li/user/{userId}/shplst", method = RequestMethod.POST)
    public ResponseEntity shplst(@PathVariable Long userId, @RequestBody List<IngredientDTO> shplst) {
        userService.addToShoppingList(userId, shplst);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/li/user/{userId}/shplst/{id}", method = RequestMethod.DELETE)
    public ResponseEntity shplstDelete(@PathVariable Long userId, @PathVariable Long id) {
        userService.deleteFromShoppingList(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
