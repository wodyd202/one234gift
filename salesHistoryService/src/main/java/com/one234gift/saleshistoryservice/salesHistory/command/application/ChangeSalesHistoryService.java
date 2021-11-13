package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.CustomerRepository;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCallReservationDate;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeCustomerReactivity;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.ChangeSalesHistoryContent;
import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.HistoryContent;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.salesHistory.command.application.SalesHistoryServiceHelper.getSalesHistory;

/**
 * 영업 기록 수정 서비스
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ChangeSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    // 외부 모듈
    private CustomerRepository customerRepository;
    /**
     * @param salesHistoryId
     * @param updater
     * # 샘플 여부 변경
     */
    public SalesHistoryModel changeSample(SalesHistoryId salesHistoryId, Writer updater) {
        // find
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        salesHistory.changeSample(updater);
        salesHistoryRepository.save(salesHistory);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        log.info("change sample sales history : {}", salesHistoryModel);
        return salesHistoryModel;
    }

    /**
     * @param salesHistoryId
     * @param updater
     * # 카탈로그 여부 변경
     */
    public SalesHistoryModel changeCatalogue(SalesHistoryId salesHistoryId, Writer updater) {
        // find
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        salesHistory.changeCatalogue(updater);
        salesHistoryRepository.save(salesHistory);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        log.info("change catalogue sales history : {}", salesHistoryModel);
        return salesHistoryModel;
    }

    /**
     * @param salesHistoryId
     * @param salesHistoryContent
     * @param updater
     * # 내용 변경
     */
    public SalesHistoryModel changeContent(SalesHistoryId salesHistoryId, ChangeSalesHistoryContent salesHistoryContent, Writer updater) {
        // find
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        salesHistory.changeContent(new HistoryContent(salesHistoryContent.getContent()), updater);
        salesHistoryRepository.save(salesHistory);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        log.info("change content sales history : {}", salesHistoryModel);
        return salesHistoryModel;
    }

    /**
     * @param salesHistoryId
     * @param callReservationDate
     * @param updater
     * # 예약콜 변경
     */
    public SalesHistoryModel changeCallReservationDate(SalesHistoryId salesHistoryId, ChangeCallReservationDate callReservationDate, Writer updater) {
        // find
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();

        Customer customer = customerRepository.getCustomer(salesHistoryModel.getCustomerId());

        salesHistory.changeCallReservationDate(callReservationDate.getCallReservationDate(), customer, updater);
        salesHistoryRepository.save(salesHistory);
        log.info("change call reservation date sales history : {}", salesHistoryModel);
        return salesHistory.toModel();
    }

    /**
     * @param salesHistoryId
     * @param customerReactivity
     * @param updater
     * # 반응도 변경
     */
    public SalesHistoryModel changeReactivity(SalesHistoryId salesHistoryId, ChangeCustomerReactivity customerReactivity, Writer updater) {
        // find
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        salesHistory.changeReactivity(customerReactivity.getReactivity(), updater);
        salesHistoryRepository.save(salesHistory);
        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        log.info("change reactivity sales history : {}", salesHistoryModel);
        return salesHistoryModel;
    }
}
