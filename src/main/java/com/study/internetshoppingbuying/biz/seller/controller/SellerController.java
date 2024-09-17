package com.study.internetshoppingbuying.biz.seller.controller;

import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;
import com.study.internetshoppingbuying.biz.seller.service.SellerService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping()
    public CommonResponseDto<Long> save(@RequestBody final SellerDto sellerDto) {
        return CommonResponseDto.OK(sellerService.save(sellerDto));
    }

    @GetMapping("/{id}")
    public CommonResponseDto<SellerDto> getSeller(@PathVariable Long id) {
        return CommonResponseDto.OK(sellerService.getSeller(id));
    }

}
