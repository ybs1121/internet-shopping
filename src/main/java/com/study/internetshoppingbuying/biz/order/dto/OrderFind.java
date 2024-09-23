package com.study.internetshoppingbuying.biz.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFind {

    private String orderId;

    private String stDt;

    private String endDt;

    private String itemNm;

}
