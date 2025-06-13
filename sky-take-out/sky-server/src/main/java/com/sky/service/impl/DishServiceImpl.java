package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavourMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {


    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavourMapper dishFlavourMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;


    /**
     * Create Dish
     *
     * @param dishDTO
     */
    @Transactional  //** either pass all or fail all
    public void createDishWithFlavour(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dish.setStatus(StatusConstant.DISABLE);

        dishMapper.insert(dish);

        //retrieve dish id from generated id refer dish mapper.xml
        Long dishId = dish.getId();

        //** batch insert flavour now
        List<DishFlavor> flavors = dishDTO.getFlavors();

        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });

            dishFlavourMapper.insertBatch(flavors);
        }
    }

    /**
     * Query Dish by page
     *
     * @param dishPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        List<DishVO> records = page.getResult();

        return new PageResult(page.getTotal(), records);

    }

    /**
     * Delete dish
     *
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //** Key logic
        //** Only delete dish that is Disable and not in set meal
        //** Delete from dish, set meal and the flavour that related to the dish

        //** check if the dish is currently enable
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);

            //** Dish is currently enable, throw error
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException("Dish is currently on sale");
            }
        }

        //** check if the dish is use in set meal
        List<Long> setMealIds = setMealDishMapper.getSetMealIdsByDishIds(ids);
        if(setMealIds != null  && setMealIds.size() >0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //** Delete from dish and flavor
        dishMapper.deleteByIds(ids);
        dishFlavourMapper.deleteByDishIds(ids);

    }

    /**
     * Get dish by id
     * @param id
     */
    @Override
    public DishVO getDishById(Long id) {
        //** Need to retrieve from dish
        //** Related flavour need to be return too


        Dish dish = dishMapper.getById(id);
        List<DishFlavor> dishFlavorList = dishFlavourMapper.getByIds(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavorList);


        return dishVO;
    }

    /**
     * Update dish
     * @param dishDTO
     */
    public void updateDish(DishDTO dishDTO) {
        //** Need to update dish
        //** delete the current flavour and re-insert the flavour from dishDTO

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //** update current dish
        dishMapper.updateDish(dish);

        //** Delete the flavour and re-insert the flavour
        dishFlavourMapper.deleteByDishId(dish.getId());
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();

        if (dishFlavorList != null && dishFlavorList.size() > 0) {
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });

            dishFlavourMapper.insertBatch(dishFlavorList);
        }


    }

    /**
     * Enable or Disable dish
     * @param status
     * @param id
     */
    public void enableOrDisableDish(Integer status, Long id) {

        //** Retrieve dish by id
        Dish dish = dishMapper.getById(id);
        dish.setStatus(status);

        //** update current dish
        dishMapper.updateDish(dish);


    }

    /**
     * Return list of dish by category id
     * @param id
     * @return
     */
    public List<Dish> getDishByCatId(Long id) {
        List<Dish> dishList = dishMapper.getByCatId(id);

        return dishList;
    }


    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();


        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //Get flavour from dish id
            List<DishFlavor> flavors = dishFlavourMapper.getByIds(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }

}
