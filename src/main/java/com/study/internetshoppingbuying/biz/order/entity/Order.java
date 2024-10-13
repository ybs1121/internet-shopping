package com.study.internetshoppingbuying.biz.order.entity;

import com.study.internetshoppingbuying.biz.user.entity.User;
import com.study.internetshoppingbuying.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    private User user;

    @Column(precision = 13)
    private Long totalPayAmount;

    @Column(precision = 13)
    private Long totalDiscountAmount;

    @Builder
    public Order(User user) {
        this.user = user;
    }
}
