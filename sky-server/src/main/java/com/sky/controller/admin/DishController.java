package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish Menu Management
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "Dish Menu Management")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("Create new dish")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("Create new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("Menu item pagination query")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Menu item pagination query: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Batch/Bulk Remove Dishes
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Batch/Bulk Remove Dishes")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Batch/Bulk Remove Dishes: {}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Get dish and flavor details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("Get dish by id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Update dish
     * @param dishDTO
     * @return
     */
    @PutMapping()
    @ApiOperation("Update dish")
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        log.info("Update dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Search dish by categoryId
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Search dish by id")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return  Result.success(list);
    }

    /**
     * Enable or Disable status
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable or Disable Category status")
    public Result<String> startOrStop(@PathVariable("status") Integer status, Long id){
        dishService.startOrStop(status,id);
        return Result.success();
    }

}
