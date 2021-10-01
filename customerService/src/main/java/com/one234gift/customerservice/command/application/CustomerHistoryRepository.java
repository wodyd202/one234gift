package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.CustomerHistory;
import com.one234gift.customerservice.domain.read.CustomerHistoryModel;

import java.util.List;

public interface CustomerHistoryRepository {
    List<CustomerHistoryModel> findById(long customerId);

    void save(CustomerHistory customerHistory);
}
