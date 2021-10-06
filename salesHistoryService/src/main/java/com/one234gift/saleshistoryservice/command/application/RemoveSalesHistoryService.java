package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import org.springframework.stereotype.Service;

import static com.one234gift.saleshistoryservice.command.application.SalesHistoryServiceHelper.existSalesHistory;

@Service
public class RemoveSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    public RemoveSalesHistoryService(SalesHistoryRepository salesHistoryRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public void remove(Long salesHistoryId, String userId) {
        verifyExistSalesHistory(salesHistoryId, userId);
        salesHistoryRepository.remove(salesHistoryId);
    }

    private void verifyExistSalesHistory(Long salesHistoryId, String userId) {
        if(!existSalesHistory(salesHistoryRepository, salesHistoryId, userId)){
            throw new SalesHistoryNotFoundException();
        }
    }
}
