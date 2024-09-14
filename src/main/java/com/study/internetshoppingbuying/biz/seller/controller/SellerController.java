package com.study.internetshoppingbuying.biz.seller.controller;

import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;
import com.study.internetshoppingbuying.biz.seller.service.SellerService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping()
    public CommonResponseDto<Long> save(@RequestBody final SellerDto sellerDto) {
        return CommonResponseDto.OK(sellerService.save(sellerDto));
    }
}
