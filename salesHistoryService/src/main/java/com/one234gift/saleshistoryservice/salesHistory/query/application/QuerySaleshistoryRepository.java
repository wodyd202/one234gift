package com.one234gift.saleshistoryservice.salesHistory.query.application;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;

import java.util.List;
import java.util.Optional;

public interface QuerySaleshistoryRepository {
    List<SalesHistoryModel> findAll(CustomerId customerId, Pageable pageable);
    long countAll(CustomerId customerId);

    Optional<SalesHistoryModel> findBySalesHistoryId(SalesHistoryId salesHistoryId);
}
