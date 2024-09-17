package com.study.internetshoppingbuying.biz.order.mapper;

import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.entity.Order;
import com.study.internetshoppingbuying.biz.user.entity.User;
import org.aspectj.weaver.ast.Or;

public class OrderMapper {

    public static Order toEntity(User user) {
        return Order.builder()
                .user(user)
                .build();
    }
}
