package com.study.internetshoppingbuying.biz.order.service;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.item.mapper.ItemMapper;
import com.study.internetshoppingbuying.biz.order.entity.Order;
import com.study.internetshoppingbuying.biz.order.entity.OrderHistory;
import com.study.internetshoppingbuying.biz.order.repository.OrderHistoryRepository;
import com.study.internetshoppingbuying.biz.order.repository.OrderRepository;
import com.study.internetshoppingbuying.biz.seller.dto.SellerDto;
import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import com.study.internetshoppingbuying.biz.seller.mapper.SellerMapper;
import com.study.internetshoppingbuying.biz.user.dto.UserDto;
import com.study.internetshoppingbuying.biz.user.entity.User;
import com.study.internetshoppingbuying.biz.user.entity.mapper.UserMapper;
import com.study.internetshoppingbuying.biz.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("mac")
class OrderServiceImplTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    OrderHistoryRepository orderHistoryRepository;


    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    void insertTest() {

        User user = UserMapper.toEntity(UserDto.builder()
                .address("address")
                .name("name")
                .userId("1234")
                .password("!@321")
                .build());
        em.persist(user);


        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Order order = Order.builder()
                    .user(user)
                    .build();
            orders.add(order);
        }

        orderRepository.saveAll(orders);
        Seller seller = SellerMapper.toEntity(SellerDto.builder()
                .sellerName("name")
                .build());

        em.persist(seller);


        Item item = Item.builder()
                .price(1000L)
                .name("123")
                .seller(seller)
                .build();
        em.persist(item);

        long start = System.currentTimeMillis();
        List<OrderHistory> orderHistories = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            OrderHistory orderHistory = OrderHistory.builder()
                    .order(orders.get(i))
                    .item(item)
                    .build();
            orderHistories.add(orderHistory);
        }
        orderHistoryRepository.saveAll(orderHistories);
        long end = System.currentTimeMillis();

        em.flush();
        System.out.println("TEST TIME ABC: " + (end - start));
        System.out.println(end - start);

    }

}