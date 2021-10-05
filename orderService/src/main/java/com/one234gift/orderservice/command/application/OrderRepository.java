package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findByIdAndUserId(long orderId, String userId);

    Optional<Order> findById(long orderId);
}
