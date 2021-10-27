package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.common.Pageable;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerHistoryService {
    private CustomerHistoryRepository customerHistoryRepository;
    private RetryTemplate retryTemplate;

    @Transactional(readOnly = true)
    public CustomerHistoryModels findAll(String customerId, Pageable pageable) {
        List<CustomerHistoryModel> customerHistoryModels = retryTemplate.execute(retryContext -> {
            log.info("load customer history : {}", customerId);
            return customerHistoryRepository.findByCustomerId(customerId, pageable);
        }, retryContext -> {
            log.error("retry error reson : {}", retryContext.getLastThrowable());
            return new ArrayList<>();
        });
        return CustomerHistoryModels.builder()
                .customerHistoryModels(customerHistoryModels)
                .totalElement(customerHistoryRepository.countByCustomerId(customerId))
                .build();
    }

    @Transactional
    public CustomerHistoryModel save(CustomerHistoryEvent customerHistoryEvent) {
        CustomerHistory customerHistory = CustomerHistory.register(customerHistoryEvent);
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
