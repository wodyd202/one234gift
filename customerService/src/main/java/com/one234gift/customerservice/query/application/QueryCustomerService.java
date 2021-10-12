package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.model.CustomerModels;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QueryCustomerService {
    private QueryCustomerRepository customerRepository;

    public QueryCustomerService(QueryCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModels findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerRepository.findAll(customerSearchDTO, pageable))
                .totalElement(customerRepository.countAll(customerSearchDTO))
                .pageable(pageable)
                .build();
    }

    public CustomerModels findMy(String manager, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerRepository.findMy(manager, pageable))
                .totalElement(customerRepository.countMy(manager))
                .pageable(pageable)
                .build();
    }

    public CustomerModel findById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public boolean existById(Long customerId) {
        return customerRepository.existById(customerId);
    }
}
