package com.study.internetshoppingbuying.biz.item.entity;

import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import com.study.internetshoppingbuying.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false, length = 200)
    private String name;

    @Column(nullable = false, precision = 13)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Builder
    public Item(String name, Long price, Seller seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }
}
