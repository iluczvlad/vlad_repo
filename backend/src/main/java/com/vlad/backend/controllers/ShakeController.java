package com.vlad.backend.controllers;

import com.vlad.backend.dto.ComplexShakeDTO;
import com.vlad.backend.dto.RecipeDTO;
import com.vlad.backend.dto.SimpleShakeDTO;
import com.vlad.backend.services.ShakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShakeController {
    private final ShakeService shakeService;

    @Autowired
    public ShakeController(ShakeService shakeService) {
        this.shakeService = shakeService;
    }

    @RequestMapping(value = "/nl/shake/simple", method = RequestMethod.GET)
    public ResponseEntity<SimpleShakeDTO> simple() {
        return new ResponseEntity<>(shakeService.generateSimpleShake(), HttpStatus.OK);
    }

    @RequestMapping(value = "/li/shake/complex", method = RequestMethod.POST)
    public ResponseEntity<ComplexShakeDTO> complex(@RequestBody RecipeDTO dto) {
        return new ResponseEntity<>(shakeService.generateComplexShake(dto), HttpStatus.OK);
    }
}
