package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;

import java.util.List;

public interface CustomerHistoryRepository {
    void save(CustomerHistory customerHistory);
    List<CustomerHistoryModel> findAll(String customerId);
}
