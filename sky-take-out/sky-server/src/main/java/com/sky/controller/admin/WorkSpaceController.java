package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.LocalTime;


@RestController
@RequestMapping("/admin/workspace")
@Slf4j
@Api(tags = "WorkSpace")
public class WorkSpaceController {

    @Autowired
    private WorkspaceService workspaceService;

    /**
     * Get Today Business Data
     * @return
     */
    @GetMapping("/businessData")
    @ApiOperation("Business Data Overview")
    public Result<BusinessDataVO> businessData(){
        //Get current day start time
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //Get current day end time
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);
        return Result.success(businessDataVO);
    }

    /**
     * Order Overview
     * @return
     */
    @GetMapping("/overviewOrders")
    @ApiOperation("Order Overview")
    public Result<OrderOverViewVO> orderOverView(){
        return Result.success(workspaceService.getOrderOverView());
    }

    /**
     * Dish Overview
     * @return
     */
    @GetMapping("/overviewDishes")
    @ApiOperation("Dish Overview")
    public Result<DishOverViewVO> dishOverView(){
        return Result.success(workspaceService.getDishOverView());
    }

    /**
     * SetMeal Overview
     * @return
     */
    @GetMapping("/overviewSetmeals")
    @ApiOperation("Set Meal Overview")
    public Result<SetmealOverViewVO> setmealOverView(){
        return Result.success(workspaceService.getSetmealOverView());
    }



}
