package com.vlad.backend.services;

import com.vlad.backend.dto.FavoriteDTO;

import java.util.List;

public interface FavoriteService {
    List<FavoriteDTO> list(String email);
    void save(FavoriteDTO dto);
    void delete(Long id);
}
