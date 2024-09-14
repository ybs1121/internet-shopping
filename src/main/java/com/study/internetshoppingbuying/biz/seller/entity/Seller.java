package com.study.internetshoppingbuying.biz.seller.entity;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String sellerName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Item> itemList = new ArrayList<>();


    @Builder
    protected Seller(Long id, String sellerName) {
        this.id = id;
        this.sellerName = sellerName;
    }
}
