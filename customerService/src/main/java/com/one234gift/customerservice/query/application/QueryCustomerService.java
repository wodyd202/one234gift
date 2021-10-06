package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QueryCustomerService {
    private QueryCustomerRepository customerRepository;

    public QueryCustomerService(QueryCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel findById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public boolean existById(Long customerId) {
        return customerRepository.existById(customerId);
    }
}
