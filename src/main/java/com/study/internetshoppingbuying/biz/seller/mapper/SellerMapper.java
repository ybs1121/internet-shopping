package com.study.internetshoppingbuying.biz.seller.mapper;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.item.mapper.ItemMapper;
import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;

import java.util.List;
import java.util.stream.Collectors;

public class SellerMapper {

    public static Seller toEntity(SellerDto sellerDto) {
        return Seller.builder()
                .sellerName(sellerDto.getSellerName())
                .build();
    }


    public static SellerDto toDto(Seller seller) {
        return SellerDto.builder()
                .id(seller.getId())
                .sellerName(seller.getSellerName())
                .build();
    }

    public static SellerDto toDto(Seller seller, List<Item> itemList) {
        return SellerDto.builder()
                .id(seller.getId())
                .sellerName(seller.getSellerName())
                .items(
                        itemList.stream().map(item -> ItemMapper.toDto(item)).collect(Collectors.toList()))
                .build();
    }
}
