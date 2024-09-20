package com.study.internetshoppingbuying.biz.order.entity;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    @Builder
    public OrderHistory(Order order, Item item) {
        this.order = order;
        this.item = item;
    }
}
