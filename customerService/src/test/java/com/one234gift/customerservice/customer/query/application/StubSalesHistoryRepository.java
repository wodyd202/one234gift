package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.customer.query.application.external.SalesHistoryModels;
import com.one234gift.customerservice.customer.query.application.external.SalesHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubSalesHistoryRepository implements SalesHistoryRepository {
    @Override
    public SalesHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
        return SalesHistoryModels.builder().build();
    }
}
