package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.Manager;
import com.one234gift.customerservice.domain.value.RegisterCustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findById;


@Service
@Slf4j
public class RegisterCustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RegisterCustomerValidator registerCustomerValidator;

    public RegisterCustomerService(UserRepository userRepository, CustomerRepository customerRepository, RegisterCustomerValidator registerCustomerValidator) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.registerCustomerValidator = registerCustomerValidator;
    }

    @Transactional
    public CustomerModel register(RegisterCustomer registerCustomer, String userId) {
        Manager manager = findById(userRepository, userId);
        Customer customer = Customer.registerWith(registerCustomer, manager);
        customer.register(registerCustomerValidator);
        customer = customerRepository.save(customer);
        log.info("save customer : {}", customer);
        return customer.toModel();
    }
}
