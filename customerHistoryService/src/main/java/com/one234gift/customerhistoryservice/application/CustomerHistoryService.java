package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.common.Pageable;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.model.CustomerHistoryEvent;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Retryable(maxAttempts = 3, include = SQLException.class, backoff = @Backoff(delay = 500))
public class CustomerHistoryService {
    private CustomerHistoryRepository customerHistoryRepository;

    @Transactional(readOnly = true)
    public List<CustomerHistoryModel> findAll(String customerId, Pageable pageable) {
        log.info("load customer history : {}", customerId);
        return customerHistoryRepository.findAll(customerId, pageable);
    }

    @Transactional
    public CustomerHistoryModel save(CustomerHistoryEvent customerHistoryEvent) {
        CustomerHistory customerHistory = CustomerHistory.register(customerHistoryEvent);
        customerHistoryRepository.save(customerHistory);
        CustomerHistoryModel customerHistoryModel = customerHistory.toModel();
        log.info("save customer history : {}", customerHistoryModel);
        return customerHistoryModel;
    }
}
