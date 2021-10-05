package com.one234gift.orderservice.application;

import com.one234gift.orderservice.domain.Order;

public interface OrderRepository {
    void save(Order order);
}
