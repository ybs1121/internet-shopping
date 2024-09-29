package com.study.internetshoppingbuying.biz.coupon.controller;

import com.study.internetshoppingbuying.biz.coupon.dto.CouponCreateDto;
import com.study.internetshoppingbuying.biz.coupon.service.CouponService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    // 쿠폰 등록
    @PostMapping()
    public CommonResponseDto<String> create(@RequestBody CouponCreateDto couponCreateDto) {
        return CommonResponseDto.OK(couponService.create(couponCreateDto));
    }


}
