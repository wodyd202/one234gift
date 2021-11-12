package com.one234gift.customerservice.customer.query.application.external;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRepositoryFallbackFactory implements FallbackFactory<OrderRepository> {
    @Override
    public OrderRepository create(Throwable throwable) {
        return new OrderRepository() {
            @Override
            public OrderModels findByCustomerId(Long customerId, Pageable pageable) {
                log.info("order-service request error : {}", throwable.getMessage());
                return new OrderModels();
            }
        };
    }
}
