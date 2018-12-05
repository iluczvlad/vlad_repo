package com.vlad.backend.controllers;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/li/ingredient/{id}", method = RequestMethod.GET)
    public ResponseEntity<IngredientDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.get(id), HttpStatus.OK);
    }

    @RequestMapping("/li/ingredient/list")
    public ResponseEntity<List<IngredientDTO>> findAll(){
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

}
