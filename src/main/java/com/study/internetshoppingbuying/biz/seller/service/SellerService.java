package com.study.internetshoppingbuying.biz.seller.service;

import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;

public interface SellerService {

    Long save(SellerDto sellerDto);


    SellerDto getSeller(Long id);
}
