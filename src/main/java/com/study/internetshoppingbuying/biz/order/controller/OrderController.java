package com.study.internetshoppingbuying.biz.order.controller;

import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.service.OrderService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CommonResponseDto<Long> order(@RequestBody final OrderDto orderDto) {
        return CommonResponseDto.OK(orderService.order(orderDto));
    }
}
