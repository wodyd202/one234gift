package com.one234gift.customerhistoryservice.presentation;

import com.one234gift.customerhistoryservice.application.CustomerHistoryModels;
import com.one234gift.customerhistoryservice.application.CustomerHistoryService;
import com.one234gift.customerhistoryservice.common.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer-history/{customerId}")
public class CustomerHistorySearchAPI {
    @Autowired
    private CustomerHistoryService customerHistoryService;

    @GetMapping
    public ResponseEntity<CustomerHistoryModels> findAll(@PathVariable String customerId, Pageable pageable){
        CustomerHistoryModels customerHistoryModels = customerHistoryService.findAll(customerId, pageable);
        return ResponseEntity.ok(customerHistoryModels);
    }
}
