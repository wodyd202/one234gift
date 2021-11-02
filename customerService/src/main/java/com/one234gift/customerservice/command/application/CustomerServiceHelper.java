package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.command.application.exception.UserNotFoundException;
import com.one234gift.customerservice.command.application.external.UserRepository;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.value.Manager;

public class CustomerServiceHelper {
    public static Manager findUser(UserRepository userRepository){
        return userRepository.findUser().orElseThrow(UserNotFoundException::new);
    }

    public static Customer findCustomer(CustomerRepository customerRepository, Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
