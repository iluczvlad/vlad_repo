package com.vlad.backend.controllers;

import com.vlad.backend.dto.FavoriteDTO;
import com.vlad.backend.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping(value = "/li/fav/list", method = RequestMethod.POST)
    public ResponseEntity<List<FavoriteDTO>> list(@RequestBody String email){
        return new ResponseEntity<>(favoriteService.list(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/fav", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody FavoriteDTO dto) {
        favoriteService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/li/fav/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
