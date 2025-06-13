package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * Create new set meal
     * @param setmealDTO
     */
    public void addSetMeal(SetmealDTO setmealDTO) {

        Setmeal setMeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setMeal);

        setMeal.setStatus(StatusConstant.DISABLE);

        setMealMapper.insert(setMeal);

        //retrieve dish id from generated id refer dish mapper.xml
        Long setMealId = setMeal.getId();

        //** batch insert flavour now
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();

        if (setmealDishList != null && setmealDishList.size() > 0) {
            setmealDishList.forEach(setmealDish -> {
                setmealDish.setSetmealId(setMealId);
            });

            setMealDishMapper.insertBatch(setmealDishList);
        }

    }

    /**
     * Query set meal by page
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> page = setMealMapper.pageQuery(setmealPageQueryDTO);
        List<SetmealVO> records = page.getResult();

        return new PageResult(page.getTotal(), records);
    }

    /**
     * Get set meal by ids
     * @param id
     * @return
     */
    public SetmealVO getSetMealById(Long id) {
        Setmeal setMeal = setMealMapper.getById(id);
        List<SetmealDish> setmealDishList = setMealDishMapper.getSetMealDishBySetMealId(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setMeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishList);


        return setmealVO;
    }

    /**
     * Update current set meal
     * @param setmealDTO
     */
    @Override
    public void updateSetMeal(SetmealDTO setmealDTO) {
        //** Need to update set meal
        //** delete the current set meal dish and re-insert the set meal dish from setmealDTO

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        //** update current set meal
        setMealMapper.updateSetMeal(setmeal);

        //** Delete the set meal dish and re-insert the set meal dish
        setMealDishMapper.deleteBySetMealId(setmeal.getId());
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();

        if (setmealDishList != null && setmealDishList.size() > 0) {
            setmealDishList.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmealDTO.getId());
            });

            setMealDishMapper.insertBatch(setmealDishList);
        }


    }

    /**
     * Enable or Disable Set Meal
     * @param status
     * @param id
     */
    public void enableOrDisableSetmeal(Integer status, Long id) {

        //** Retrieve dish by id
        Setmeal setmeal = setMealMapper.getById(id);
        setmeal.setStatus(status);

        //** update current dish
        setMealMapper.updateSetMeal(setmeal);

    }

    /**
     * Delete set meal by batch
     * @param ids
     */
    public void deleteBatch(List<Long> ids) {
        //** Key logic
        //** Only delete set meal that is Disable
        //** Delete from set meal, set meal dish

        //** check if the dish is currently enable
        for (Long id : ids) {
            Setmeal setmeal = setMealMapper.getById(id);

            //** Set Meal is currently enable, throw error
            if (setmeal.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException("Set Meal is currently on sale");
            }

            setMealDishMapper.deleteBySetMealId(id);
        }

        setMealMapper.deleteByIds(ids);


    }

    /**
     *
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setMealMapper.list(setmeal);
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setMealMapper.getDishItemBySetmealId(id);
    }
}
