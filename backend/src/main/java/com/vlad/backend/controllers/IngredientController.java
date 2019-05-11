package com.vlad.backend.controllers;

import com.vlad.backend.dto.IngredientDTO;
import com.vlad.backend.model.Type;
import com.vlad.backend.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/li/ingredient/type/{type}/{email}", method = RequestMethod.GET)
    public ResponseEntity<List<IngredientDTO>> get(@PathVariable Type type, @PathVariable String email) {
        return new ResponseEntity<>(ingredientService.get(type, email), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/ingredient", method = RequestMethod.POST)
    public void save(@RequestBody IngredientDTO dto) {
        ingredientService.save(dto);
    }

    @RequestMapping("/li/ingredient/list")
    public ResponseEntity<List<IngredientDTO>> findAll(){
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/ingredient/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        ingredientService.delete(id);
    }

}
