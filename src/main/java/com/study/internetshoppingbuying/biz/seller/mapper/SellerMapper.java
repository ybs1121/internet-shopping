package com.study.internetshoppingbuying.biz.seller.mapper;

import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;

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
}
