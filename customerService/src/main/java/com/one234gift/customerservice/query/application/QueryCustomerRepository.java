package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.domain.read.CustomerModel;

import java.util.Optional;

public interface QueryCustomerRepository {
    Optional<CustomerModel> findById(long customerId);
}
