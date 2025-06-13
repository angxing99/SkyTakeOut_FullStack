package com.sky.controller.user;


import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/shoppingCart")
@Slf4j
public class ShopCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @PostMapping("/add")
    @ApiOperation("Add item to cart")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){

        shoppingCartService.addToCart(shoppingCartDTO);

        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("Return all item from cart")
    public Result<List<ShoppingCart>> list(){

        List<ShoppingCart> shoppingCartList = shoppingCartService.list();


        return Result.success(shoppingCartList);
    }

    @DeleteMapping("/clean")
    @ApiOperation("Delete all item from cart")
    public Result deleteFromCart(){

        shoppingCartService.deleteFromCart();

        return Result.success();
    }


    @PostMapping("/sub")
    @ApiOperation("Delete item from cart by id")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("delete item from cart，item：{}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }

}
