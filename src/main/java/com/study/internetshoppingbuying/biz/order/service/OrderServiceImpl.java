package com.study.internetshoppingbuying.biz.order.service;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.item.repository.ItemRepository;
import com.study.internetshoppingbuying.biz.item.service.ItemService;
import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.entity.Order;
import com.study.internetshoppingbuying.biz.order.entity.OrderHistory;
import com.study.internetshoppingbuying.biz.order.mapper.OrderMapper;
import com.study.internetshoppingbuying.biz.order.repository.OrderHistoryRepository;
import com.study.internetshoppingbuying.biz.order.repository.OrderRepository;
import com.study.internetshoppingbuying.biz.user.entity.User;
import com.study.internetshoppingbuying.biz.user.repository.UserRepository;
import com.study.internetshoppingbuying.biz.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    public Long order(OrderDto orderDto) {
        // 주문 회원 조회
        User user = userRepository.findByUserId(orderDto.getUserId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재 하지 않는 회원입니다.");
                }
        );


        // 품목 조회
        if (orderDto.getItemIdList() == null || orderDto.getItemIdList().isEmpty()) {
            throw new IllegalArgumentException("주문하려는 품목이 존재하지 않습니다.");
        }

        List<Item> itemList = itemRepository.findByIdIn(orderDto.getItemIdList());
        if (itemList.size() != orderDto.getItemIdList().size()) {
            throw new IllegalArgumentException("존재 하지 않는 품목이 있습니다.");
        }
        // Order 원장 생성

        Order order = OrderMapper.toEntity(user);
        orderRepository.save(order);

        // OrderHistory - 상세  생성
        for (Item item : itemList) {
            OrderHistory orderHistory = OrderHistory.builder()
                    .order(order)
                    .item(item)
                    .build();

            orderHistoryRepository.save(orderHistory);

        }
        return order.getId();
    }
}
