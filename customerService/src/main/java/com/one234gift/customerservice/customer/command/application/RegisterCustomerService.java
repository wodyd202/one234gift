package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.customer.command.application.model.RegisterCustomer;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.customer.command.application.event.RegisteredCustomerEvent;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * 고객 생성 서비스
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegisterCustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private RegisterCustomerValidator registerCustomerValidator;
    private ApplicationEventPublisher applicationEventPublisher;

    @Retryable(maxAttempts = 3, include = SQLException.class, backoff = @Backoff(delay = 500))
    public CustomerModel register(RegisterCustomer registerCustomer) {
        // map
        Customer customer = customerMapper.mapFrom(registerCustomer);
        customer.register(registerCustomerValidator);

        // save
        customer = customerRepository.save(customer);
        log.info("save customer into database : {}", customer);

        // publish event
        CustomerModel customerModel = customer.toModel();
        applicationEventPublisher.publishEvent(new RegisteredCustomerEvent(
                customerModel.getId(),
                customerModel.getCategory(),
                customerModel.getBusinessInfo(),
                customerModel.getPurchasingManagers(),
                customerModel.getAddress(),
                customerModel.getFax(),
                customerModel.getCreateDate()
        ));
        return customerModel;
    }

    @Recover
    private void retryFallback(Exception e){
        e.printStackTrace();
        log.error(e.toString());
        throw new DataBaseNotAccessAbleException();
    }
}

