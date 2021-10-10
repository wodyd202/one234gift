package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;

import java.util.List;
import java.util.Optional;

public interface QueryCustomerRepository {
    List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable);
    Optional<CustomerModel> findById(long customerId);
    boolean existById(Long customerId);
}
