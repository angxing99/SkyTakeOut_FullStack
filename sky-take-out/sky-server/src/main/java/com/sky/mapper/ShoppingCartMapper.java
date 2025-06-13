package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {


    /**
     * List out current shopping cart
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);


    /**
     * Update cart copies based on id
     *
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id} ")
    void updateNumberById(ShoppingCart shoppingCart);


    @Insert(
            "INSERT INTO shopping_cart (" +
                    "name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time" +
                    ") VALUES (" +
                    "#{name}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{image}, #{createTime}" +
                    ")"
    )
    void insert(ShoppingCart shoppingCart);

    /**
     * Delete all item from cart
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteAllItem(Long userId);

    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);


    @Delete("delete from shopping_cart where user_id = #{id}")
    void deleteByUserId(Long id);


    /**
     * Batch insert item to shopping cart
     *
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
