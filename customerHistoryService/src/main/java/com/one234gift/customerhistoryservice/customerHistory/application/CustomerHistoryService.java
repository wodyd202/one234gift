package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryModels;
import com.one234gift.customerhistoryservice.application.model.Pageable;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.application.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 고객 변경 이력 서비스
 */
@Service
@Slf4j
@AllArgsConstructor
public class CustomerHistoryService {
    private CustomerHistoryMapper customerHistoryMapper;
    private CustomerHistoryRepository customerHistoryRepository;
    private RetryTemplate retryTemplate;

    /**
     * @param customerId
     * @param pageable
     * # 고객 수정 이력 리스트 조회
     */
    @Transactional(readOnly = true)
    public CustomerHistoryModels getCustomerHistorys(long customerId, Pageable pageable) {
        return CustomerHistoryModels.builder()
                .customerHistoryModels(getCustomerHistoryModels(customerId, pageable))
                .totalElement(customerHistoryRepository.countByCustomerId(customerId))
                .build();
    }

    private List<CustomerHistoryModel> getCustomerHistoryModels(long customerId, Pageable pageable) {
        return retryTemplate.execute(retryContext -> {
            log.info("load customer history : {}", customerId);
            return customerHistoryRepository.findByCustomerId(customerId, pageable);
        }, retryContext -> {
            log.error("retry error reson : {}", retryContext.getLastThrowable());
            return new ArrayList<>();
        });
    }

    /**
     * @param customerHistoryEvent
     * # 고객 수정 이력 저장
     */
    @Transactional
    public CustomerHistoryModel save(CustomerHistoryEvent customerHistoryEvent) {
        CustomerHistory customerHistory = customerHistoryMapper.mapFrom(customerHistoryEvent);
        return save(customerHistory);
    }

    private CustomerHistoryModel save(CustomerHistory customerHistory) {
        return retryTemplate.execute(retryContext -> {
            customerHistoryRepository.save(customerHistory);
            CustomerHistoryModel customerHistoryModel = customerHistory.toModel();
            log.info("save customer history : {}", customerHistoryModel);
            return customerHistoryModel;
        }, retryContext -> {
            log.error("retry error reson : {}", retryContext.getLastThrowable());
            return null;
        });
    }

}
