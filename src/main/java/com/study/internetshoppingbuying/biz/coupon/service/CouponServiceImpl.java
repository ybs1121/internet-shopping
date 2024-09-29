package com.study.internetshoppingbuying.biz.coupon.service;

import com.study.internetshoppingbuying.biz.coupon.dto.CouponCreateDto;
import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;
import com.study.internetshoppingbuying.biz.coupon.mapper.CouponMapper;
import com.study.internetshoppingbuying.biz.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public String create(CouponCreateDto couponCreateDto) {
        //UUID 생성
        String couponId = generate16DigitString();

        //쿠폰 저장
        log.info("couponId: {}", couponId);
        log.info("couponCreateDto: {}", couponCreateDto);
        Coupon coupon = CouponMapper.toEntity(couponCreateDto, couponId);
        couponRepository.save(coupon);
        return couponId;
    }


    private static String generate16DigitString() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "");
        return uuidString.substring(0, 16);
    }
}
