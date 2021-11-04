package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.read.ResponsibleModel;
import com.one234gift.customerservice.query.application.model.CustomerSearchDTO;

import java.util.List;
import java.util.Optional;

public interface QueryCustomerListRepository {
    List<CustomerModel> findByResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager, Pageable pageable);
    List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable);
    Optional<CustomerModel> findById(long customerId);
    long countAll(CustomerSearchDTO customerSearchDTO);
    long countByManager(CustomerSearchDTO customerSearchDTO, String manager);
    boolean existById(Long customerId);
    List<ResponsibleModel> findResponsibleUsers(Long customerId);
}
