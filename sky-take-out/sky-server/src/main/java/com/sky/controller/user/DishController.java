package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Get Dish by category Id")
    public Result<List<DishVO>> list(Long categoryId) {

        // Build Redis Key using dish + category_id
        String key = "dish_" + categoryId;

        //Check if redis contain cache value
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list != null && list.size() > 0) {
            log.info("cache exist");
            return Result.success(list);
        }

        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);

        // Add data to redis cache
        list = dishService.listWithFlavor(dish);
        // Expire after 30 minute
        redisTemplate.opsForValue().set(key, list, Duration.ofMinutes(30));

        return Result.success(list);
    }
}
