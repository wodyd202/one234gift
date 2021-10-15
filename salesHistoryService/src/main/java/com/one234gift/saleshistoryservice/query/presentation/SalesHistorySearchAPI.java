package com.one234gift.saleshistoryservice.query.presentation;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.query.application.QuerySalesHistoryService;
import com.one234gift.saleshistoryservice.query.model.SalesHistoryModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer/{customerId}/sales-history")
public class SalesHistorySearchAPI {
    @Autowired private QuerySalesHistoryService salesHistoryService;

    @GetMapping
    public ResponseEntity<SalesHistoryModels> findAll(@PathVariable long customerId, Pageable pageable){
        SalesHistoryModels salesHistoryModels = salesHistoryService.findAll(customerId, pageable);
        return ResponseEntity.ok(salesHistoryModels);
    }
}
