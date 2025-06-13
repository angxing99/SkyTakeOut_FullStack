package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;


    @PutMapping("/{status}")
    @ApiOperation("Update shop status")
    public Result setStatus(@PathVariable Integer status) {

        //** Update shop status into redis
        redisTemplate.opsForValue().set(KEY, status);


        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("Get Shop Status")
    public Result<Integer> getStatus() {

        //** Update shop status into redis
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("Current shop status:  {}", status == 1 ? "Online" : "Offline");

        return Result.success(status);
    }


}
