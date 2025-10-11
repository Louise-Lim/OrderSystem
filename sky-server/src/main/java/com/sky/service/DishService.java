package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * Create Dish with Corresponding Flavors
     * @param dishDTO
     */
    void saveWithFlavor (DishDTO dishDTO);

    /**
     * Menu item pagination query
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Batch/Bulk Remove Dishes
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get dish and flavor details by id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Update dish and flavor by id
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);
}
