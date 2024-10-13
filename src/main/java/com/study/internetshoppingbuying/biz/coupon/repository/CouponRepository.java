package com.study.internetshoppingbuying.biz.coupon.repository;

import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, String> {

    List<Coupon> findByIdIn(Set<String> ids);
}
