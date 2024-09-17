package com.study.internetshoppingbuying.biz.seller.dto;

import com.study.internetshoppingbuying.biz.item.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SellerDto {

    private Long id;
    private String sellerName;
    private List<ItemDto> items;
}
