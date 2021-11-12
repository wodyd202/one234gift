package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findByIdAndUserId(long orderId, String userId);

    Optional<Order> findById(long orderId);
}
