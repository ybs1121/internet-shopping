package com.study.internetshoppingbuying.biz.order.service;

import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.dto.OrderFind;
import com.study.internetshoppingbuying.biz.order.dto.OrderResponseDto;

public interface OrderService {

    Long order(OrderDto orderDto);

    OrderResponseDto getOrders(String userId, OrderFind orderFind);


}
