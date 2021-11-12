package com.one234gift.customerservice.customer.query.application.external;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesHistoryRepositoryFallbackFactory implements FallbackFactory<SalesHistoryRepository> {
    @Override
    public SalesHistoryRepository create(Throwable throwable) {
        return new SalesHistoryRepository() {
            @Override
            public SalesHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
                log.info("sales-history-service request error : {}", throwable.getMessage());
                return new SalesHistoryModels();
            }
        };
    }
}
