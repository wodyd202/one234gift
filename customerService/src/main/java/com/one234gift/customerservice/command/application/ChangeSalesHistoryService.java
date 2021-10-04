package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.SalesHistory;
import com.one234gift.customerservice.domain.model.ChangeCallReservationDate;
import com.one234gift.customerservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.customerservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.SalesHistoryServiceHelper.findSalesHistory;

@Service
@Transactional
public class ChangeSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    public ChangeSalesHistoryService(SalesHistoryRepository salesHistoryRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public SalesHistoryModel changeSample(Long id, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, id, userId);
        salesHistory.changeSample();
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeCatalogue(Long id, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, id, userId);
        salesHistory.changeCatalogue();
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeContent(Long id, ChangeSalesHistoryContent salesHistoryContent, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, id, userId);
        salesHistory.changeContent(salesHistoryContent);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeCallReservationDate(Long id, ChangeCallReservationDate callReservationDate, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, id, userId);
        salesHistory.changeCallReservationDate(callReservationDate);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    public SalesHistoryModel changeReactivity(Long id, ChangeCustomerReactivity customerReactivity, String userId) {
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, id, userId);
        salesHistory.changeReactivity(customerReactivity);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }
}
