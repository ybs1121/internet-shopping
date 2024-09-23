package com.study.internetshoppingbuying.biz.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDto {

    private String userId;
    private List<OrderDto> orderDtoList = new ArrayList<>();



    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class OrderDto {

        private Long orderId;
        private String itemNm;
        private Long price;
        private String sellerNm;

    }
}
