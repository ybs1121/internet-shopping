package com.study.internetshoppingbuying.biz.order.entity;

import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;
import com.study.internetshoppingbuying.biz.item.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
        name = "order_history_gen"
        , allocationSize = 50
        , initialValue = 1
        , sequenceName = "order_history_seq"
)
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @Column(precision = 13)
    private Long payAmount;

    @Column(precision = 13)
    private Long discountAmount;


    @Builder

    public OrderHistory(Long id, Order order, Item item, Coupon coupon, Long payAmount, Long discountAmount) {
        this.order = order;
        this.item = item;
        this.coupon = coupon;
        this.payAmount = payAmount;
        this.discountAmount = discountAmount;
    }
}
