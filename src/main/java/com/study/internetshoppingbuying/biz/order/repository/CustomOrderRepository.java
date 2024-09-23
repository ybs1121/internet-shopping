package com.study.internetshoppingbuying.biz.order.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.internetshoppingbuying.biz.item.entity.QItem;
import com.study.internetshoppingbuying.biz.order.dto.OrderFind;
import com.study.internetshoppingbuying.biz.order.dto.OrderResponseDto;
import com.study.internetshoppingbuying.biz.order.entity.QOrder;
import com.study.internetshoppingbuying.biz.order.entity.QOrderHistory;
import com.study.internetshoppingbuying.biz.seller.entity.QSeller;
import com.study.internetshoppingbuying.biz.user.entity.QUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomOrderRepository {

    private final JPAQueryFactory queryFactory;


    //Custom Dto 반환

    public OrderResponseDto getOrders(String userId, OrderFind orderFind) {
        QOrderHistory orderHistory = QOrderHistory.orderHistory;
        QOrder order = QOrder.order;
        QItem item = QItem.item;
        QSeller seller = QSeller.seller;
        QUser user = QUser.user;
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        List<OrderResponseDto.OrderDto> orderDtoList = queryFactory.selectFrom(orderHistory)
                .join(orderHistory.order, order)
                .join(orderHistory.item, item)
                .leftJoin(order.user, user)
                .leftJoin(item.seller, seller)
                .where(
                        userIdEq(userId), itemNmLike(orderFind.getItemNm()), dateBetween(orderFind.getStDt(), orderFind.getEndDt())
                )
                .transform(
                        GroupBy.groupBy(orderHistory.id).list(
                                Projections.fields(OrderResponseDto.OrderDto.class
                                        , order.id.as("orderId")
                                        , item.name.as("itemNm")
                                        , item.price
                                        , seller.sellerName.as("sellerNm")
                                )
                        )

                );

        orderResponseDto.setUserId(userId);
        orderResponseDto.setOrderDtoList(orderDtoList);

        return orderResponseDto;

    }

    private BooleanExpression userIdEq(String userId) {
        return QUser.user.userId.eq(userId);
    }

    private BooleanExpression itemNmLike(String itemNm) {
        if (itemNm == null) return null;
        return QItem.item.name.contains(itemNm);
    }

    private BooleanExpression dateBetween(String stDt, String endDt) {
        if (stDt == null) return null;
        if (endDt == null) return null;
        LocalDate stDtLocal = parseDt(stDt);
        LocalDate endDtLocal = parseDt(endDt);

        return QOrder.order.createdAt.between(stDtLocal.atStartOfDay(), endDtLocal.atStartOfDay().plusDays(1L));

    }


    public LocalDate parseDt(String dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            return LocalDate.parse(dt, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYYMMDD.");
        }
    }


}
