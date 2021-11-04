package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.query.application.external.OrderModels;
import com.one234gift.customerservice.query.application.external.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubOrderRepository implements OrderRepository {
    @Override
    public OrderModels findByCustomerId(Long customerId, Pageable pageable) {
        return null;
    }
}
