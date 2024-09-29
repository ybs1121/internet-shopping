package com.study.internetshoppingbuying.biz.coupon.repository;

import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {

}
