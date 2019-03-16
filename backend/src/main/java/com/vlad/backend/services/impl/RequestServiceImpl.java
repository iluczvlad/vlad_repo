package com.vlad.backend.services.impl;

import com.vlad.backend.dto.NewOrderDTO;
import com.vlad.backend.model.Request;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.RequestRepository;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void place(NewOrderDTO dto) {
        User byEmail = userRepository.findByEmail(dto.getEmail());
        Request request = new Request();
        request.setBasic(byEmail);
        request.setAddress(dto.getAddress());
        request.setPhoneNr(dto.getPhone());
        request.setPlacementDate(new Date());
        request.setOrderItems(byEmail.getShoppingList());
        byEmail.setShoppingList(new ArrayList<>());
        requestRepository.save(request);
    }
}
