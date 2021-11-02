package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.query.application.external.CustomerHistoryModels;
import com.one234gift.customerservice.query.application.external.CustomerHistoryRepository;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerHistoryFallbackFactory implements FallbackFactory<CustomerHistoryRepository> {
    @Override
    public CustomerHistoryRepository create(Throwable throwable) {
        return new CustomerHistoryRepository() {
            @Override
            public CustomerHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
                log.info("customer-history-service request error : {}", throwable.getMessage());
                return CustomerHistoryModels.builder().build();
            }
        };
    }
}
