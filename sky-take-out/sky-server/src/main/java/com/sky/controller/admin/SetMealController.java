package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminSetMealController")
@RequestMapping("admin/setmeal")
@Slf4j
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    @PostMapping
    @ApiOperation("Create Set Meal")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public Result addSetMeal(@RequestBody SetmealDTO setmealDTO){

        setMealService.addSetMeal(setmealDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("Query Setmeal by page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){

        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);

        return Result.success(pageResult);

    }

    @GetMapping("/{id}")
    @ApiOperation("Query Setmeal by page")
    public Result page(@PathVariable Long id){

        SetmealVO setmealVO = setMealService.getSetMealById(id);

        return Result.success(setmealVO);

    }

    @PutMapping()
    @ApiOperation("Query Setmeal by page")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result updateSetMeal(@RequestBody SetmealDTO setmealDTO){

        setMealService.updateSetMeal(setmealDTO);

        return Result.success();
    }

    @PostMapping("status/{status}")
    @ApiOperation("Enable or Disable Set Meal")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result enableOrDisableDish(@PathVariable Integer status, Long id){

        setMealService.enableOrDisableSetmeal(status, id);


        return Result.success();
    }

    @DeleteMapping()
    @ApiOperation("Delete Set meal")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result deleteSetmeal(@RequestParam List<Long> ids){

        setMealService.deleteBatch(ids);
        return Result.success();
    }
}
