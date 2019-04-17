package com.vlad.backend.repositories;

import com.vlad.backend.model.OurRecipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OurRecipeRepository extends JpaRepository<OurRecipe, Long> {
}
