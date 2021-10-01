package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.domain.Customer;

public class CustomerServiceHelper {
    public static Customer findById(CustomerRepository customerRepository, Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
