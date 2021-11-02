package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.query.application.external.CustomerHistoryModels;
import com.one234gift.customerservice.query.application.external.CustomerHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubCustomerHistoryRepository implements CustomerHistoryRepository {
    @Override
    public CustomerHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
        return CustomerHistoryModels.builder().build();
    }
}
