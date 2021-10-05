package com.one234gift.orderservice.application;

import com.one234gift.orderservice.application.exception.CustomerNotFoundException;
import com.one234gift.orderservice.application.exception.SalesUserNotFoundException;
import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;

public class OrderServiceHelper {
    public static SalesUser findUser(UserRepository userRepository) {
        return userRepository.findUser().orElseThrow(SalesUserNotFoundException::new);
    }

    public static CustomerInfo findById(CustomerRepository customerRepository, long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
