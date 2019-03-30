package com.vlad.backend.services;

import com.vlad.backend.dto.NewOrderDTO;
import com.vlad.backend.dto.RequestDTO;

import java.util.List;

public interface RequestService {

    void place(NewOrderDTO dto);

    List<RequestDTO> getAll();

    void confirm(RequestDTO dto);
}
