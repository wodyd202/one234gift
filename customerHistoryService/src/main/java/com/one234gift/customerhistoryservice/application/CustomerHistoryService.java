package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Setter
@Transactional(readOnly = true)
public class CustomerHistoryService {
    private CustomerHistoryRepository customerHistoryRepository;

    public CustomerHistoryService(CustomerHistoryRepository customerHistoryRepository) {
        this.customerHistoryRepository = customerHistoryRepository;
    }

    public List<CustomerHistoryModel> findAll(String customerId) {
        return customerHistoryRepository.findAll(customerId);
    }
}
