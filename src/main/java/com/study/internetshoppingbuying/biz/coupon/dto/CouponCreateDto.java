package com.study.internetshoppingbuying.biz.coupon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.internetshoppingbuying.biz.coupon.CouponTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CouponCreateDto {

    private CouponTypeEnum type;

    private Long price;

    private Long maxDiscountPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate endTime;
}
