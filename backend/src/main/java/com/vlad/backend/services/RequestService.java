package com.vlad.backend.services;

import com.vlad.backend.dto.NewOrderDTO;

public interface RequestService {

    void place(NewOrderDTO dto);

}
