package com.vlad.backend.services;

import com.vlad.backend.dto.ComplexShakeDTO;
import com.vlad.backend.dto.RecipeDTO;
import com.vlad.backend.dto.SimpleShakeDTO;

public interface ShakeService {
    SimpleShakeDTO generateSimpleShake();
    ComplexShakeDTO generateComplexShake(RecipeDTO dto);
}
