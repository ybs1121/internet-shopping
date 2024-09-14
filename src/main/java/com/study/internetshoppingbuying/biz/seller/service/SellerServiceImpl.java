package com.study.internetshoppingbuying.biz.seller.service;

import com.study.internetshoppingbuying.biz.seller.mapper.SellerMapper;
import com.study.internetshoppingbuying.biz.seller.respository.SellerRepository;
import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public Long save(SellerDto sellerDto) {
        if(StringUtils.isEmpty(sellerDto.getSellerName())) {
            throw new IllegalArgumentException("판매자 명은 없을 수 없습니다.");
        }
        return sellerRepository.save(SellerMapper.toEntity(sellerDto)).getId();
    }
}
