package com.sky.controller.user;


import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/submit")
    @ApiOperation("Create new order")
    public Result<OrderSubmitVO> createOrder(@RequestBody OrdersSubmitDTO ordersSubmitDTO){

        OrderSubmitVO orderSubmitVO = orderService.createNewOrder(ordersSubmitDTO);

        return Result.success(orderSubmitVO);
    }


    @GetMapping("/historyOrders")
    @ApiOperation("Get Order History")
    public Result<PageResult> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO){

        PageResult pageResult =  orderService.pageQuery(ordersPageQueryDTO);

        return Result.success(pageResult);
    }

    @GetMapping("/orderDetail/{id}")
    @ApiOperation("Check order detail")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }


    @PutMapping("/cancel")
    @ApiOperation("Cancel order")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    @PostMapping("/repetition/{id}")
    @ApiOperation("Replicate Order")
    public Result replicateOrder(@PathVariable Long id){

        orderService.replicateOrder(id);

        return Result.success();
    }


    @GetMapping("/reminder/{id}")
    @ApiOperation("Customer remind order")
    public Result reminder(@PathVariable Long id){
        orderService.remindOrder(id);
        return Result.success();
    }




}
