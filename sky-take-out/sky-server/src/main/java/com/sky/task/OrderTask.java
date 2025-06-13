package com.sky.task;


import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Cancels orders that have been pending payment for more than 15 minutes.
     */
    @Scheduled(cron ="0 * * * * ? ") //Trigger every minute
    public void processTimeOutOrder(){
        log.info("process time out order: {}", LocalDateTime.now());

        List<Orders> ordersList=  orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,LocalDateTime.now().plusMinutes(-15));

        if (ordersList != null && ordersList.size() > 0){

            for(Orders orders: ordersList){
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("Order Time out");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * Marks orders that have been DELIVERY_IN_PROGRESS for over 60 minutes as COMPLETED.
     */
    @Scheduled(cron = "0 0 1 * * ?") //Trigger every 1am
    public void processDeliveryOrder(){

        log.info("process delivery order: {}", LocalDateTime.now());


        List<Orders> ordersList=  orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS,LocalDateTime.now().plusMinutes(-60));

        if (ordersList != null && ordersList.size() > 0){

            for(Orders orders: ordersList){
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }

    }
}
