package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {

    /**
     * Create Dish with Corresponding Flavors
     * @param dishDTO
     */
    void saveWithFlavor (DishDTO dishDTO);
}
