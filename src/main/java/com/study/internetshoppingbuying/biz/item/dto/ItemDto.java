package com.study.internetshoppingbuying.biz.item.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private Long id;

    private String name;

    private Long price;

    private Long sellerId;

}
