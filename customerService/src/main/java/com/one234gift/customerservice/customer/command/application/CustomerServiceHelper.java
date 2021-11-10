package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.exception.UserNotFoundException;
import com.one234gift.customerservice.customer.command.application.external.UserRepository;
import com.one234gift.customerservice.customer.command.application.util.ProcessUserIdGetter;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.exception.CustomerNotFoundException;
import com.one234gift.customerservice.customer.domain.value.Manager;

public class CustomerServiceHelper {
    public static Manager findUser(UserRepository userRepository, ProcessUserIdGetter processUseridGetter){
        return userRepository.findUser(processUseridGetter.get()).orElseThrow(UserNotFoundException::new);
    }

    public static Customer findCustomer(CustomerRepository customerRepository, Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
