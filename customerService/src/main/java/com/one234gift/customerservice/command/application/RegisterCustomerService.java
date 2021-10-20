package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@Service
@Transactional
public class RegisterCustomerService {
    private final CustomerRepository customerRepository;
    private final RegisterCustomerValidator registerCustomerValidator;

    public RegisterCustomerService(CustomerRepository customerRepository, RegisterCustomerValidator registerCustomerValidator) {
        this.customerRepository = customerRepository;
        this.registerCustomerValidator = registerCustomerValidator;
    }

    @Retryable(maxAttempts = 3, include = SQLException.class, backoff = @Backoff(delay = 500))
    public CustomerModel register(RegisterCustomer registerCustomer) {
        Customer customer = Customer.registerWith(registerCustomer);
        customer.register(registerCustomerValidator);
        customer = customerRepository.save(customer);
        log.info("save customer : {}", customer);
        return customer.toModel();
    }

    @Recover
    private CustomerModel fallback(){
        throw new DataBaseNotAccessAbleException();
    }
}

