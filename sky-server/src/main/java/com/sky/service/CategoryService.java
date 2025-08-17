package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;

public interface CategoryService {

    /**
     * Add New Category
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Category Pagination Query
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Delete Category by id
     * @param id
     */
    void deleteById(Long id);

    /**
     * Update Category
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * Enable or Disable Category status
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * Query Categories by Type
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
