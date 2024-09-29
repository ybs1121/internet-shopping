package com.study.internetshoppingbuying.biz.order.controller;

import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.dto.OrderFind;
import com.study.internetshoppingbuying.biz.order.dto.OrderResponseDto;
import com.study.internetshoppingbuying.biz.order.service.OrderService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CommonResponseDto<Long> order(@RequestBody final OrderDto orderDto) {
        return CommonResponseDto.OK(orderService.order(orderDto));
    }

    @GetMapping("/{userId}")
    public CommonResponseDto<OrderResponseDto> getOrder(@PathVariable final String userId, OrderFind orderFind) {
        return CommonResponseDto.OK(orderService.getOrders(userId, orderFind));
    }
}
