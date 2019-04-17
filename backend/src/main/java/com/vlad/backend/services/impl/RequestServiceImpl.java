package com.vlad.backend.services.impl;

import com.vlad.backend.dto.NewOrderDTO;
import com.vlad.backend.dto.RequestDTO;
import com.vlad.backend.dto.ShoppingItemDTO;
import com.vlad.backend.dto.UserDTO;
import com.vlad.backend.model.Request;
import com.vlad.backend.model.ShoppingItem;
import com.vlad.backend.model.User;
import com.vlad.backend.repositories.RequestRepository;
import com.vlad.backend.repositories.UserRepository;
import com.vlad.backend.services.IngredientService;
import com.vlad.backend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final IngredientService ingredientService;
    private final GmailClientImpl gmailClient;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository, IngredientService ingredientService, GmailClientImpl gmailClient) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.ingredientService = ingredientService;
        this.gmailClient = gmailClient;
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

    @Override
    public List<RequestDTO> getAll() {
        return requestRepository.findAll().stream()
                .filter(request -> request.getConfirmationDate()==null)
                .map(request -> {
                    RequestDTO requestDTO = new RequestDTO();
                    requestDTO.setId(request.getId());
                    requestDTO.setAddress(request.getAddress());

                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(request.getBasic().getName());
                    userDTO.setShoppingList(request.getOrderItems().stream()
                            .map(this::toShoppingItemDTO)
                            .collect(Collectors.toList()));

                    requestDTO.setBasic(userDTO);
                    requestDTO.setPhoneNr(request.getPhoneNr());
                    requestDTO.setPlacementDate(request.getPlacementDate());
                    return requestDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void confirm(RequestDTO dto) {
        Request request = requestRepository.findById(dto.getId()).get();
        request.setConfirmationDate(new Date());
        request.setClerk(userRepository.findById(dto.getClerkId()).get());
        requestRepository.save(request);
        gmailClient.sendMail(request);
    }

    private ShoppingItemDTO toShoppingItemDTO(ShoppingItem item){
        ShoppingItemDTO dto = new ShoppingItemDTO();
        dto.setId(item.getId());
        dto.setIngredients(item.getIngredients().stream().map(ingredientService::toDto).collect(Collectors.toList()));
        return dto;
    }
}
