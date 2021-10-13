package com.one234gift.orderservice.query.application;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.domain.read.OrderModel;

import java.util.List;
import java.util.Optional;

public interface QueryOrderRepository {
    List<OrderModel> findAll(String userId, Pageable pageable);
    Optional<OrderModel> findById(long orderId);

    long countAll(String userId);
}
