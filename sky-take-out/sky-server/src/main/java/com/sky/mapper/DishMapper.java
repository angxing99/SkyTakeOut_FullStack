package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper  {


    /**
     * Query from dish using category id
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    /**
     * Create new dish
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);


    /**
     * Query dish by page
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Retrieve dish by dish id
     * @param id
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Select("select * from dish where category_id = #{id}")
    List<Dish> getByCatId(Long id);

    /**
     * Delete from dish by id
     * @param id
     */
    @Delete("Delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * Batch delete from dish
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * Update dish
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateDish(Dish dish);



    /**
     *
     *
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);


    /**
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
