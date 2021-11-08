package com.one234gift.saleshistoryservice.query.presentation;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.query.application.QuerySalesHistoryService;
import com.one234gift.saleshistoryservice.query.model.SalesHistoryModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 영업 기록 조회 API
 */
@RestController
@RequestMapping("api/sales-history")
public class SalesHistorySearchAPI {
    @Autowired private QuerySalesHistoryService salesHistoryService;

    /**
     * @param customerId
     * @param pageable
     * # 영업 기록 리스트 조회
     */
    @GetMapping("customer/{customerId}")
    public ResponseEntity<SalesHistoryModels> getSalesHistoryModels(@PathVariable long customerId, Pageable pageable){
        SalesHistoryModels salesHistoryModels = salesHistoryService.getSalesHistoryModelsByCustomerId(customerId, pageable);
        return ResponseEntity.ok(salesHistoryModels);
    }

    /**
     * @param salesHistoryId
     * # 영업 기록 단건 조회
     */
    @GetMapping("{salesHistoryId}")
    public ResponseEntity<SalesHistoryModel> getSalesHistoryModel(@PathVariable long salesHistoryId){
        SalesHistoryModel salesHistoryModel = salesHistoryService.getSalesHistoryModel(salesHistoryId);
        return ResponseEntity.ok(salesHistoryModel);
    }

}
