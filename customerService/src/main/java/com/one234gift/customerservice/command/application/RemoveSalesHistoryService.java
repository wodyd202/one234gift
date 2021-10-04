package com.one234gift.customerservice.command.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.SalesHistoryServiceHelper.findSalesHistory;

@Service
@Transactional
public class RemoveSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    public RemoveSalesHistoryService(SalesHistoryRepository salesHistoryRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public void remove(Long id, String userId) {
        findSalesHistory(salesHistoryRepository, id, userId);
        salesHistoryRepository.remove(id);
    }
}
