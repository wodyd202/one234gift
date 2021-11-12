package com.one234gift.orderservice.order.query.application;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.order.domain.read.OrderModel;

import java.util.List;
import java.util.Optional;

public interface QueryOrderRepository {
    List<OrderModel> findByUserId(String userId, Pageable pageable);
    List<OrderModel> findAllByCustomerId(long customerId, Pageable pageable);
    Optional<OrderModel> findById(long orderId);

    long countByUserId(String userId);
    long countByCustomerId(long customerId);
}
