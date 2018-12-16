package com.vlad.backend.repositories;

import com.vlad.backend.model.Favorite;
import com.vlad.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUser(User user);
    List<Favorite> findAllByUser(User user);
}
