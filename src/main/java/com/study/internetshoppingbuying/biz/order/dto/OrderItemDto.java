package com.study.internetshoppingbuying.biz.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private Long itemId;
    private String couponId;
}
