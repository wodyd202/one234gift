package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.customer.query.application.external.OrderModels;
import com.one234gift.customerservice.customer.query.application.external.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubOrderRepository implements OrderRepository {
    @Override
    public OrderModels findByCustomerId(Long customerId, Pageable pageable) {
        return null;
    }
}
