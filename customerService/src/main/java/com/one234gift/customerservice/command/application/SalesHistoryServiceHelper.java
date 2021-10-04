package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.customerservice.domain.SalesHistory;

public class SalesHistoryServiceHelper {
    public static SalesHistory findSalesHistory(SalesHistoryRepository salesHistoryRepository, Long id, String userId) {
        return salesHistoryRepository.findByIdAndUserId(id,userId).orElseThrow(SalesHistoryNotFoundException::new);
    }
}
