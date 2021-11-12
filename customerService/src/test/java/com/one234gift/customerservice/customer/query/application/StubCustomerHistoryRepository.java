package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.customer.query.application.external.CustomerHistoryModels;
import com.one234gift.customerservice.customer.query.application.external.CustomerHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubCustomerHistoryRepository implements CustomerHistoryRepository {
    @Override
    public CustomerHistoryModels findLatelyByCustomerId(long customerId, Pageable pageable) {
        return new CustomerHistoryModels();
    }
}
