package com.one234gift.orderservice.order.command.application.external;

import com.one234gift.orderservice.order.domain.value.CustomerInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CustomerRepositoryFallbackFactory implements FallbackFactory<CustomerRepository> {
    @Override
    public CustomerRepository create(Throwable throwable) {
        return new CustomerRepository() {
            @Override
            public Optional<CustomerInfo> findById(long customerId) {
                log.info("customer-service request error : {}", throwable.getMessage());
                return Optional.empty();
            }
        };
    }
}
