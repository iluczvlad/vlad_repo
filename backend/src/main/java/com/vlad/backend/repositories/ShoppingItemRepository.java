package com.vlad.backend.repositories;

import com.vlad.backend.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
}
