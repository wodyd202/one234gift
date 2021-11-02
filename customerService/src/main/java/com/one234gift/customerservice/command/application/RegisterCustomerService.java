package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.RegisteredCustomerEvent;
import com.one234gift.customerservice.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegisterCustomerService {
    private CustomerRepository customerRepository;
    private RegisterCustomerValidator registerCustomerValidator;
    private ApplicationEventPublisher applicationEventPublisher;


    @Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 1000))
    public CustomerModel register(RegisterCustomer registerCustomer) {
        // map
        Customer customer = Customer.registerWith(registerCustomer);
        customer.register(registerCustomerValidator);

        // save
        customer = customerRepository.save(customer);
        log.info("save customer into database : {}", customer);

        // toModel
        CustomerModel customerModel = customer.toModel();
        applicationEventPublisher.publishEvent(new RegisteredCustomerEvent(customerModel));
        return customerModel;
    }

    @Recover
    private void retryFallback(Exception e){
        e.printStackTrace();
        log.error(e.toString());
        throw new DataBaseNotAccessAbleException();
    }
}

