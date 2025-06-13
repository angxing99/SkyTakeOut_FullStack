package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;


    @Override
    public void addToCart(ShoppingCartDTO shoppingCartDTO) {

        // 1. check if the item already exist in shopping cart
        // 2. if the item already inside then only update the copies
        // 3. if not inside then create new record

        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> list =  shoppingCartMapper.list(shoppingCart);

        //  Item already exist
        if(list != null && list.size() > 0){
            // Get the first item and update the number of copies
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);

        }
        else{
            Long dishId = shoppingCartDTO.getDishId();

            // user adding a new dish to cart
            if(dishId != null) {
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());

            }
            else{
                Long setmealId = shoppingCartDTO.getSetmealId();

                Setmeal setmeal = setMealMapper.getById(setmealId);

                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());

            }

            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);

        }




    }

    /**
     * Return all item from cart
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        // retrieve current user id
        Long userId = BaseContext.getCurrentId();

        ShoppingCart shoppingCart =   ShoppingCart.builder().userId(userId).build();

        List<ShoppingCart>  shoppingCartList = shoppingCartMapper.list(shoppingCart);

        return shoppingCartList;
    }

    /**
     * Delete all item from cart
     */

    public void deleteFromCart() {
        Long userId = BaseContext.getCurrentId();


        shoppingCartMapper.deleteAllItem(userId);


    }

    /**
     * Delete item from cart by id
     * @param shoppingCartDTO
     */
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        // here will only return 1 item
        if(list != null && list.size() > 0){
            shoppingCart = list.get(0);

            Integer number = shoppingCart.getNumber();
            if(number == 1){
                // delete item from cart
                shoppingCartMapper.deleteById(shoppingCart.getId());
            }else {
                // update the item amount if more than 1
                shoppingCart.setNumber(shoppingCart.getNumber() - 1);
                shoppingCartMapper.updateNumberById(shoppingCart);
            }
        }
    }
}
