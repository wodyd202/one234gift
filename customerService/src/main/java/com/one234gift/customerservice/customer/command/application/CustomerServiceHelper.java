package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.exception.UserNotFoundException;
import com.one234gift.customerservice.customer.command.application.external.Employee;
import com.one234gift.customerservice.customer.command.application.external.EmployeeRepository;
import com.one234gift.customerservice.customer.command.application.model.Changer;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.exception.CustomerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceHelper {
    public static Employee getEmployee(EmployeeRepository userRepository, Changer changer){
        return userRepository.getEmployee(changer.get()).orElseThrow(UserNotFoundException::new);
    }

    public static Customer getCustomer(CustomerRepository customerRepository, Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
