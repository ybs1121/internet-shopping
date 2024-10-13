package com.study.internetshoppingbuying.biz.order.service;

import com.study.internetshoppingbuying.biz.coupon.CouponTypeEnum;
import com.study.internetshoppingbuying.biz.coupon.entity.Coupon;
import com.study.internetshoppingbuying.biz.coupon.mapper.CouponMapper;
import com.study.internetshoppingbuying.biz.coupon.repository.CouponRepository;
import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.item.repository.ItemRepository;
import com.study.internetshoppingbuying.biz.order.dto.OrderDto;
import com.study.internetshoppingbuying.biz.order.dto.OrderFind;
import com.study.internetshoppingbuying.biz.order.dto.OrderItemDto;
import com.study.internetshoppingbuying.biz.order.dto.OrderResponseDto;
import com.study.internetshoppingbuying.biz.order.entity.Order;
import com.study.internetshoppingbuying.biz.order.entity.OrderHistory;
import com.study.internetshoppingbuying.biz.order.mapper.OrderMapper;
import com.study.internetshoppingbuying.biz.order.repository.CustomOrderRepository;
import com.study.internetshoppingbuying.biz.order.repository.OrderHistoryRepository;
import com.study.internetshoppingbuying.biz.order.repository.OrderRepository;
import com.study.internetshoppingbuying.biz.user.entity.User;
import com.study.internetshoppingbuying.biz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CouponRepository couponRepository;

    public Long order(OrderDto orderDto) {
        // 주문 회원 조회
        User user = userRepository.findByUserId(orderDto.getUserId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재 하지 않는 회원입니다.");
                }
        );


        // 품목 조회
        if (orderDto.getOrderItemList() == null || orderDto.getOrderItemList().isEmpty()) {
            throw new IllegalArgumentException("주문하려는 품목이 존재하지 않습니다.");
        }

        // 품목 존재 유무 검증
        Set<Long> itemIdSet = orderDto.getOrderItemList().stream().map(OrderItemDto::getItemId).collect(Collectors.toSet());

        List<Item> itemList = itemRepository.findByIdIn(itemIdSet);
        if (itemList.size() != itemIdSet.size()) {
            throw new IllegalArgumentException("존재 하지 않는 품목이 있습니다.");
        }

        // 쿠폰 유효성 검사
        Set<String> couponIdSet = orderDto.getOrderItemList().stream().filter(orderItemDto -> !StringUtils.isEmpty(orderItemDto.getCouponId()))
                .map(OrderItemDto::getCouponId).collect(Collectors.toSet());
        if (couponIdSet.size() > 0) {
            List<Coupon> couponIdList = couponRepository.findByIdIn(couponIdSet);

            if (couponIdSet.size() != couponIdList.size()) {
                throw new IllegalArgumentException("존재 하지 않는 쿠폰이 있습니다.");
            }
        }

        // 쿠폰 적용


        // Order 원장 생성
        Order order = OrderMapper.toEntity(user);
        orderRepository.save(order);

        // OrderHistory - 상세  생성
        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        //fixme 불필요한 DB 조회가 많음 추후 수정 필요
        for (OrderItemDto item : orderDto.getOrderItemList()) {
            OrderHistory orderHistory = OrderHistory.builder()
                    .order(order)
                    .item(itemRepository.findById(item.getItemId()).orElseThrow())
                    .coupon(couponRepository.findById(item.getCouponId()).orElseThrow())
                    .payAmount(calculatePayAmount(item))
                    .build();
            orderHistoryList.add(orderHistory);

//            orderHistoryRepository.save(orderHistory);
        }
        orderHistoryRepository.saveAll(orderHistoryList);
        log.info("주문 완료");
        return order.getId();
    }

    @Override
    public OrderResponseDto getOrders(String userId, OrderFind orderFind) {
        try {
            return customOrderRepository.getOrders(userId, orderFind);
        } catch (Exception e) {
            log.error("getOrders error : {} {}", e.getClass().getName(), e.getMessage());
            throw e;
        }
    }


    private Long calculatePayAmount(OrderItemDto orderItemDto) {
        Item item = itemRepository.findById(orderItemDto.getItemId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재 하지 않는 품목이 있습니다.");
                }
        );


        if (orderItemDto.getCouponId() == null || orderItemDto.getCouponId().isEmpty()) {
            return item.getPrice();
        }

        Coupon coupon = couponRepository.findById(orderItemDto.getCouponId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재 하지 않는 쿠폰이 있습니다.");
                }
        );

        long finalItemPrice = 0;

        if (CouponTypeEnum.DISCOUNT.equals(coupon.getType())) {
            finalItemPrice = item.getPrice() - coupon.getPrice();
            if (finalItemPrice < 0) {
                finalItemPrice = 0;
            }
        } else if (CouponTypeEnum.PERCENTAGE.equals(coupon.getType())) {
            Long discountPrice = (long) (item.getPrice() * (coupon.getPrice() * 0.01));

            if (discountPrice > coupon.getMaxDiscountPrice()) {
                discountPrice = coupon.getMaxDiscountPrice();
            }
            finalItemPrice = item.getPrice() - discountPrice;

        }


        return finalItemPrice;
    }
}
