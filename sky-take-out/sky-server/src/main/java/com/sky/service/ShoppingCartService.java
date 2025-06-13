package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {


    /**
     * Add item to cart
     * @param shoppingCartDTO
     */
    void addToCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * Return all item in cart
     * @return
     */
    List<ShoppingCart> list();

    /**
     * Delete all item from cart
     */
    void deleteFromCart();

    /**
     * Delete item from cart by id
     * @param id
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
