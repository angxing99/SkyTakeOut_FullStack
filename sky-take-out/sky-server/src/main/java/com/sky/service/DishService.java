package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {


    /**
     * Create Dish
     *
     * @param dishDTO
     */
    void createDishWithFlavour(DishDTO dishDTO);


    /**
     * Query dish by page
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);


    /**
     * Delete dish
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get dish by id
     * @param id
     */
    DishVO getDishById(Long id);

    /**
     * Update dish
     * @param dishDTO
     */
    void updateDish(DishDTO dishDTO);

    /**
     * Enable or Disable dish
     * @param status
     * @param id
     */
    void enableOrDisableDish(Integer status, Long id);

    /**
     * Get list of dish by category id
     * @param id
     * @return
     */
    List<Dish> getDishByCatId(Long id);


    /**
     *
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
