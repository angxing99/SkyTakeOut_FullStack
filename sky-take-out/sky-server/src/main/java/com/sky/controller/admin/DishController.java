package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping
    @ApiOperation("Create new dish")
    public Result createDish(@RequestBody DishDTO dishDTO) {

        String key = "dish_" + dishDTO.getCategoryId();

        dishService.createDishWithFlavour(dishDTO);


        cleanCache(key);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("Query Dish by page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {

        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);

        return Result.success(pageResult);

    }

    /**
     * Delete dish
     *
     * @param ids
     * @return
     */
    @DeleteMapping()
    @ApiOperation("Delete Dish")
    public Result deleteDish(@RequestParam List<Long> ids) {

        dishService.deleteBatch(ids);

        cleanCache("dish_*");


        return Result.success();
    }

    /**
     * Get dish by Id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get Dish By Id")
    public Result<DishVO> getDishById(@PathVariable Long id) {


        DishVO dishVO = dishService.getDishById(id);

        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("Update dish")
    public Result updateDish(@RequestBody DishDTO dishDTO) {

        dishService.updateDish(dishDTO);
        cleanCache("dish_*");

        return Result.success();
    }

    @PostMapping("status/{status}")
    public Result enableOrDisableDish(@PathVariable Integer status, Long id) {

        dishService.enableOrDisableDish(status, id);

        cleanCache("dish_*");

        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("Get Dish By Id")
    public Result getDishByCatId(@RequestParam Long categoryId) {

        List<Dish> dishVO = dishService.getDishByCatId(categoryId);

        return Result.success(dishVO);
    }


    private void cleanCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }


}
