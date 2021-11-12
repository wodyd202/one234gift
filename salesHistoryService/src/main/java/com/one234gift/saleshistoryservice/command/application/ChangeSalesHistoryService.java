package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.HistoryContent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.command.application.SalesHistoryServiceHelper.findSalesHistory;

/**
 * 영업 기록 수정 서비스
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ChangeSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    public SalesHistoryModel changeSample(Long salesHistoryId, String userId) {
        return action(salesHistoryId, userId, (salesHistory)->{
            salesHistory.changeSample();
        });
    }

    public SalesHistoryModel changeCatalogue(Long salesHistoryId, String userId) {
        return action(salesHistoryId, userId, (salesHistory)->{
            salesHistory.changeCatalogue();
        });
    }

    public SalesHistoryModel changeContent(Long salesHistoryId, ChangeSalesHistoryContent salesHistoryContent, String userId) {
        return action(salesHistoryId, userId, (salesHistory)->{
            HistoryContent changeContent = new HistoryContent(salesHistoryContent.getContent());
            salesHistory.changeContent(changeContent);
        });
    }

    public SalesHistoryModel changeCallReservationDate(Long salesHistoryId, ChangeCallReservationDate callReservationDate, String userId) {
        return action(salesHistoryId, userId, (salesHistory)->{
            salesHistory.changeCallReservationDate(callReservationDate.getCallReservationDate());
        });
    }

    public SalesHistoryModel changeReactivity(Long salesHistoryId, ChangeCustomerReactivity customerReactivity, String userId) {
        return action(salesHistoryId, userId, (salesHistory)->{
            salesHistory.changeReactivity(customerReactivity);
        });
    }

    // 중복 코드 제거
    private SalesHistoryModel action(Long salesHistoryId, String userId,Action action){
        SalesHistory salesHistory = findSalesHistory(salesHistoryRepository, salesHistoryId, userId);
        action.process(salesHistory);
        salesHistoryRepository.save(salesHistory);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        log.info("change customer history : {}", salesHistoryModel);
        return salesHistoryModel;
    }

    interface Action {
        void process(SalesHistory salesHistory);
    }
}
