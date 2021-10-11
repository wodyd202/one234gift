package com.one234gift.customerhistoryservice.presentation;

import com.one234gift.customerhistoryservice.application.CustomerHistoryService;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer-history/{customerId}")
public class CustomerHistorySearchAPI {
    @Autowired
    private CustomerHistoryService customerHistoryService;

    @GetMapping
    public ResponseEntity<List<CustomerHistoryModel>> findAll(@PathVariable String customerId){
        List<CustomerHistoryModel> customerHistoryModels = customerHistoryService.findAll(customerId);
        return ResponseEntity.ok(customerHistoryModels);
    }
}
