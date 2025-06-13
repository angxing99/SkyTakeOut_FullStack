package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {


    /**
     * Create new set meal
     * @param setmealDTO
     */
    void addSetMeal(SetmealDTO setmealDTO);

    /**
     * Query set meal by page
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Get set meal by id
     * @param id
     * @return
     */
    SetmealVO getSetMealById(Long id);

    /**
     * Update current set meal
     * @param setmealDTO
     */
    void updateSetMeal(SetmealDTO setmealDTO);

    /**
     * Enable or Disable set meal
     * @param status
     * @param id
     */
    void enableOrDisableSetmeal(Integer status, Long id);

    /**
     * Delete set meal by batch
     * @param ids
     */
    void deleteBatch(List<Long> ids);



    /**
     *
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);


    /**
     *
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

}
