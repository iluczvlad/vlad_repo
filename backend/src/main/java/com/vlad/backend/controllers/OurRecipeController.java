package com.vlad.backend.controllers;

import com.vlad.backend.dto.OurRecipeDTO;
import com.vlad.backend.services.OurRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OurRecipeController {
    private final OurRecipeService ourRecipeService;

    @Autowired
    public OurRecipeController(OurRecipeService ourRecipeService) {
        this.ourRecipeService = ourRecipeService;
    }

    @RequestMapping(value = "/li/or/list", method = RequestMethod.GET)
    public ResponseEntity<List<OurRecipeDTO>> list(){
        return new ResponseEntity<>(ourRecipeService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/or", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody OurRecipeDTO dto) {
        ourRecipeService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/li/or/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        ourRecipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
