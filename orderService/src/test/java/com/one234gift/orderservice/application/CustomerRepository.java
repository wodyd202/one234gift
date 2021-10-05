package com.one234gift.orderservice.application;

import com.one234gift.orderservice.domain.value.CustomerInfo;

import java.util.Optional;

public interface CustomerRepository {
    Optional<CustomerInfo> findById(long customerId);
}
