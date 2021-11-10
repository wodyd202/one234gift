package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.read.ResponsibleModel;
import com.one234gift.customerservice.customer.query.application.model.CustomerSearchDTO;

import java.util.List;
import java.util.Optional;

public interface QueryCustomerListRepository {
    List<CustomerModel> findByResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager, Pageable pageable);
    List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable);
    Optional<CustomerModel> findById(long customerId);
    long countByResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager);
    long countAll(CustomerSearchDTO customerSearchDTO);
    List<ResponsibleModel> findResponsibleUsers(Long customerId);
}
