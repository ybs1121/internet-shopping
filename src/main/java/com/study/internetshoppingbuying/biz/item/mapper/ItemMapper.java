package com.study.internetshoppingbuying.biz.item.mapper;

import com.study.internetshoppingbuying.biz.item.dto.ItemDto;
import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.seller.entity.Seller;

public class ItemMapper {

    public static Item toEntity(ItemDto itemDto, Seller seller) {
        return Item.builder()
                .price(itemDto.getPrice())
                .name(itemDto.getName())
                .seller(seller)
                .build();
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }
}
