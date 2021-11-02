package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.domain.read.CustomerModel;

import java.util.Optional;

public interface QueryCustomerRepository {
    void save(CustomerModel customerModel);
    Optional<CustomerModel> findById(long customerId);
    boolean existById(Long customerId);
}
