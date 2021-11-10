package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.domain.read.CustomerModel;

import java.util.Optional;

public interface QueryCustomerRepository {
    void save(CustomerModel customerModel);
    Optional<CustomerModel> findById(long customerId);
    boolean existById(Long customerId);
}
