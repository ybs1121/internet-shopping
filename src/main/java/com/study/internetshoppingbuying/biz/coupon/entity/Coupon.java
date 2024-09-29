package com.study.internetshoppingbuying.biz.coupon.entity;

import com.study.internetshoppingbuying.biz.coupon.CouponTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

    @Id
    @Column(name = "coupon_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type")
    private CouponTypeEnum type;

    private Long price;

    private Long maxDiscountPrice;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate startTime;

    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate endTime;

    @Builder
    public Coupon(String id, CouponTypeEnum type, Long price, Long maxDiscountPrice, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.maxDiscountPrice = maxDiscountPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
