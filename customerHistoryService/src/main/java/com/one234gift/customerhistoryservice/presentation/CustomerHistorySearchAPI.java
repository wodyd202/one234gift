package com.one234gift.customerhistoryservice.presentation;

import com.one234gift.customerhistoryservice.application.model.CustomerHistoryModels;
import com.one234gift.customerhistoryservice.application.CustomerHistoryService;
import com.one234gift.customerhistoryservice.application.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 고객 수정 이력 조회 API
 */
@RestController
@RequestMapping("api/customer-history/{customerId}")
public class CustomerHistorySearchAPI {
    @Autowired
    private CustomerHistoryService customerHistoryService;

    /**
     * @param customerId
     * @param pageable
     * # 해당 고객 수정 이력 리스트 조회
     */
    @GetMapping
    public ResponseEntity<CustomerHistoryModels> getCustomerHistoryModels(@PathVariable long customerId, Pageable pageable){
        CustomerHistoryModels customerHistoryModels = customerHistoryService.getCustomerHistorys(customerId, pageable);
        return ResponseEntity.ok(customerHistoryModels);
    }
}
