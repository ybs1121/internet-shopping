package com.study.internetshoppingbuying.biz.order.repository;

import com.study.internetshoppingbuying.biz.order.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
