package com.study.internetshoppingbuying.biz.order.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {

    private String userId;
    private List<Long> itemIdList;
}
