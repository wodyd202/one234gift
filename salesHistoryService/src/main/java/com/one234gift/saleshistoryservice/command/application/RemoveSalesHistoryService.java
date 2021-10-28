package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.exception.SalesHistoryNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.command.application.SalesHistoryServiceHelper.existSalesHistory;

/**
 * 영업 기록 삭제
 */
@Service
@Transactional
@AllArgsConstructor
public class RemoveSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

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
