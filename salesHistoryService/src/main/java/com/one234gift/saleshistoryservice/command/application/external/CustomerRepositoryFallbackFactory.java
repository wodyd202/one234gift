package com.one234gift.saleshistoryservice.command.application;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerRepositoryFallbackFactory implements FallbackFactory<CustomerRepository> {
    @Override
    public CustomerRepository create(Throwable throwable) {
        return new CustomerRepository() {
            @Override
            public boolean existByCustomer(long customerId) {
                log.info("customer-service request error : {}", throwable.getMessage());
                return false;
            }
        };
    }
}