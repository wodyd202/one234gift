package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.query.application.external.SalesHistoryModels;
import com.one234gift.customerservice.query.application.external.SalesHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubSalesHistoryRepository implements SalesHistoryRepository {
    @Override
    public SalesHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
        return SalesHistoryModels.builder().build();
    }
}
