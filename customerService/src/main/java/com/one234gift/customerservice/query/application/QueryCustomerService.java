package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.model.CustomerModels;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 고객 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class QueryCustomerService {
    private QueryCustomerRepository customerRepository;
    private CustomerHistoryRepository customerHistoryRepository;

    /**
     * 모든 고객 조회
     * @param customerSearchDTO
     * @param pageable
     */
    public CustomerModels findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerRepository.findAll(customerSearchDTO, pageable))
                .totalElement(customerRepository.countAll(customerSearchDTO))
                .pageable(pageable)
                .build();
    }

    /**
     * 나의 고객 조회
     * @param manager
     * @param pageable
     */
    public CustomerModels findByManager(String manager, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerRepository.findByManager(manager, pageable))
                .totalElement(customerRepository.countByManager(manager))
                .pageable(pageable)
                .build();
    }

    /**
     * 고객 단건조회
     * @param customerId
     */
    public CustomerModel findById(Long customerId) {
        CustomerModel customerModel = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerModel.addLatelyHistorys(customerHistoryRepository.findLatelyByCustomerId(customerId, new Pageable(0, 10)));
        return customerModel;
    }

    /**
     * 고객 존재여부 확인
     * @param customerId
     */
    public boolean existById(Long customerId) {
        return customerRepository.existById(customerId);
    }
}
