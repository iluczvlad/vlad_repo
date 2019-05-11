package com.vlad.backend.services;

import com.vlad.backend.dto.OurRecipeDTO;

import java.util.List;

public interface OurRecipeService {
    List<OurRecipeDTO> findAll();
    void save(OurRecipeDTO dto);
    void delete(Long id);
}
