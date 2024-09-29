package com.study.internetshoppingbuying.biz.coupon.mapper;

import com.study.internetshoppingbuying.biz.coupon.dto.CouponCreateDto;
import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;

public class CouponMapper {

    public static Coupon toEntity(CouponCreateDto createDto, String couponId) {
        return Coupon.builder()
                .id(couponId)
                .price(createDto.getPrice())
                .type(createDto.getType())
                .startTime(createDto.getStartTime())
                .endTime(createDto.getEndTime())
                .maxDiscountPrice(createDto.getMaxDiscountPrice())
                .build();
    }
}
