package com.one234gift.customerservice.query.application.external;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "SALESHISTORY-SERVICE", configuration = FeignClientConfig.class, fallbackFactory = CustomerHistoryFallbackFactory.class)
public interface SalesHistoryRepository {
    @GetMapping("api/sales-history/customer/{customerId}")
    SalesHistoryModels findLatelyByCustomerId(long customerId, @SpringQueryMap Pageable pageable);
}
