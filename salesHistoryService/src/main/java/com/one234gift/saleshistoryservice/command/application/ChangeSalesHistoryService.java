package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.command.application.SalesHistoryServiceHelper.findSalesHistory;

@Service
@Transactional
public class ChangeSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    public ChangeSalesHistoryService(SalesHistoryRepository salesHistoryRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public SalesHistoryModel changeSample(Long salesHistoryId, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        salesHistory.changeSample();
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeCatalogue(Long salesHistoryId, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        salesHistory.changeCatalogue();
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeContent(Long salesHistoryId, ChangeSalesHistoryContent salesHistoryContent, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        salesHistory.changeContent(salesHistoryContent);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeCallReservationDate(Long salesHistoryId, ChangeCallReservationDate callReservationDate, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        salesHistory.changeCallReservationDate(callReservationDate);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeReactivity(Long salesHistoryId, ChangeCustomerReactivity customerReactivity, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        salesHistory.changeReactivity(customerReactivity);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }
}
