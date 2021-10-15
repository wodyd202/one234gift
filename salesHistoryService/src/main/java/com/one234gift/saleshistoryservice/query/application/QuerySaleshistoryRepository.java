package com.one234gift.saleshistoryservice.query.application;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;

import java.util.List;

public interface QuerySaleshistoryRepository {
    List<SalesHistoryModel> findAll(long customerId, Pageable pageable);
    long countAll(long customerId);
}
