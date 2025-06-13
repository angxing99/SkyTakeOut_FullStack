package com.sky.service;

import com.github.pagehelper.Page;
import com.sky.dto.*;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

import java.util.List;

public interface OrderService {

    /**
     * Create new order
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO createNewOrder(OrdersSubmitDTO ordersSubmitDTO);


    /**
     * Return past order history
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * Get Order detail by id
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * Cancel order
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * Replicate order
     * @param id
     */
    void replicateOrder(Long id);

    /**
     * Admin search for order
     * @param ordersPageQueryDTO
     * @return PageResult
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO statistics();

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    void rejection(OrdersRejectionDTO ordersRejectionDTO);


    void delivery(Long id);

    void complete(Long id);

     void paySuccess(String outTradeNo);

    void remindOrder(Long id);
}
