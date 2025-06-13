package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface DishFlavourMapper {

    /**
     * Batch insert flavour from dish
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * Delete flavour with dish id
     * @param dishId
     */
    @Delete("Delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * Batch delete flavor with dish id
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * Get list of flavor by dish id
     * @param dishId
     * @return
     */
    @Select("Select * from dish_flavor where dish_id = #{dishId} ")
    List<DishFlavor> getByIds(Long dishId);


    /**
     * Update flavor by dish
     * @param flavors
     * @param id
     */
    void updateFlavorByDish(List<DishFlavor> flavors, Long id);
}
