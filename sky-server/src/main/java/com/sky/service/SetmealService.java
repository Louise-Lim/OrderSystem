package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Enable and Disable status
     * @param status
     * @param id
     * @return
     */
    void setmealStatus(Integer status, Long id);

    /**
     * Batch delete or delete setmeal by ids
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get setmeal detail by id
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * Update setmeal
     * @param setmealDTO
     * @return
     */
    void update(SetmealDTO setmealDTO);
}
