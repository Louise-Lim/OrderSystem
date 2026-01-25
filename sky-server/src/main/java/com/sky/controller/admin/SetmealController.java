package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api("Setmeal Relevant API")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation("Add setmeal")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("Setmeal page query list")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("Setmeal page query list: {}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Enable and Disable status
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable and Disable status")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Enable and Disable status : {}, {}", status,id);
        setmealService.setmealStatus(status, id);
        return Result.success();
    }

    /**
     * Batch delete or delete setmeal by ids
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Batch delete setmeal")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("Batch delete setmeal: {}", ids);
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Get setmeal detail by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get setmeal detail by id")
    public Result<SetmealVO> getDetailsById(@PathVariable Long id) {
        log.info("Get setmeal detail by id : {}", id);
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * Update setmeal
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update setmeal")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("Update setmeal : {}", setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

}
