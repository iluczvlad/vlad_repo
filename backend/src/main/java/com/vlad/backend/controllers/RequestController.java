package com.vlad.backend.controllers;

import com.vlad.backend.dto.NewOrderDTO;
import com.vlad.backend.dto.RequestDTO;
import com.vlad.backend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(value = "/li/request/place", method = RequestMethod.POST)
    public void place(@RequestBody NewOrderDTO dto){
        requestService.place(dto);
    }

    @RequestMapping(value = "/li/request/all", method = RequestMethod.GET)
    public List<RequestDTO> getAll(){
        return requestService.getAll();
    }

    @RequestMapping(value = "/li/request/confirm", method = RequestMethod.POST)
    public void confirm(@RequestBody RequestDTO dto){
        requestService.confirm(dto);
    }
}
