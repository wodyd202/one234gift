package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.domain.exception.CustomerNotFoundException;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.query.application.external.CustomerHistoryRepository;
import com.one234gift.customerservice.customer.query.application.external.OrderRepository;
import com.one234gift.customerservice.customer.query.application.external.SalesHistoryRepository;
import com.one234gift.customerservice.customer.query.application.model.CustomerModels;
import com.one234gift.customerservice.customer.query.application.model.CustomerSearchDTO;
import com.one234gift.customerservice.customer.query.application.model.Pageable;
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
    private QueryCustomerListRepository customerListRepository;

    // 캐시 스토어
    private QueryCustomerRepository customerRepository;

    // 외부 모듈
    private CustomerHistoryRepository customerHistoryRepository;
    private SalesHistoryRepository salesHistoryRepository;
    private OrderRepository orderRepository;

    /**
     * 모든 고객 조회
     * @param customerSearchDTO
     * @param pageable
     */
    public CustomerModels getCustomerModels(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerListRepository.findAll(customerSearchDTO, pageable))
                .totalElement(customerListRepository.countAll(customerSearchDTO))
                .pageable(pageable)
                .build();
    }

    /**
     * 나의 고객 조회
     * @param customerSearchDTO
     * @param manager
     * @param pageable
     */
    public CustomerModels getCustomerModelsByTargetResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager, Pageable pageable) {
        return CustomerModels.builder()
                .customers(customerListRepository.findByResponsibleUser(customerSearchDTO, manager, pageable))
                .totalElement(customerListRepository.countByResponsibleUser(customerSearchDTO, manager))
                .pageable(pageable)
                .build();
    }

    /**
     * 고객 단건조회
     * @param customerId
     */
    public CustomerModel getCustomerModel(Long customerId) {
        CustomerModel customerModel = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Pageable latelyPageable = getTop10Pageable();

        // 최근 변경 이력 조회
        customerModel.addLatelyHistorys(customerHistoryRepository.findLatelyByCustomerId(customerId, latelyPageable));

        // 최근 메모 이력 조회
        customerModel.addLatelySalesHistorys(salesHistoryRepository.findLatelyByCustomerId(customerId, latelyPageable));

        // 최근 주문 이력 조회
        customerModel.addLatelyOrders(orderRepository.findByCustomerId(customerId, latelyPageable));

        // 해당 고객 담당자 조회
        customerModel.addResponsibleUsers(customerListRepository.findResponsibleUsers(customerId));
        return customerModel;
    }

    private Pageable getTop10Pageable() {
        return new Pageable(0, 10);
    }

    /**
     * 고객 존재여부 확인
     * @param customerId
     */
    public boolean existCustomer(Long customerId) {
        return customerRepository.existById(customerId);
    }
}
