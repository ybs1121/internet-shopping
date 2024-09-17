package com.study.internetshoppingbuying.biz.order.repository;

import com.study.internetshoppingbuying.biz.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
