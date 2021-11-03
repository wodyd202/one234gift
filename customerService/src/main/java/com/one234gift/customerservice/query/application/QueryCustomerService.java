package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.external.CustomerHistoryRepository;
import com.one234gift.customerservice.query.application.external.SalesHistoryRepository;
import com.one234gift.customerservice.query.application.model.CustomerModels;
import com.one234gift.customerservice.query.application.model.CustomerSearchDTO;
import lombok.AllArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 고객 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 1000))
public class QueryCustomerService {
    private QueryCustomerListRepository customerListRepository;

    // 캐시 스토어
    private QueryCustomerRepository customerRepository;

    // 외부 모듈
    private CustomerHistoryRepository customerHistoryRepository;
    private SalesHistoryRepository salesHistoryRepository;

    /**
     * 모든 고객 조회
     * @param customerSearchDTO
     * @param pageable
     */
    public CustomerModels findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerListRepository.findAll(customerSearchDTO, pageable))
                .totalElement(customerListRepository.countAll(customerSearchDTO))
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
                .customers(customerListRepository.findByManager(manager, pageable))
                .totalElement(customerListRepository.countByManager(manager))
                .pageable(pageable)
                .build();
    }

    /**
     * 고객 단건조회
     * @param customerId
     */
    public CustomerModel findById(Long customerId) {
        CustomerModel customerModel = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Pageable latelyPageable = getTop10Pageable();

        // 최근 변경 이력 조회
        customerModel.addLatelyHistorys(customerHistoryRepository.findLatelyByCustomerId(customerId, latelyPageable));

        // 최근 메모 이력 조회
        customerModel.addLatelySalesHistorys(salesHistoryRepository.findLatelyByCustomerId(customerId, latelyPageable));
        return customerModel;
    }

    private Pageable getTop10Pageable() {
        return new Pageable(0, 10);
    }

    /**
     * 고객 존재여부 확인
     * @param customerId
     */
    public boolean existById(Long customerId) {
        return customerRepository.existById(customerId);
    }
}
