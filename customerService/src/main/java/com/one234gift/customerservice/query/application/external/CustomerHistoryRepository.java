package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.config.FeignClientConfig;
import com.one234gift.customerservice.query.application.model.external.CustomerHistoryModels;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-HISTORY-SERVICE", configuration = FeignClientConfig.class, fallbackFactory = CustomerHistoryFallbackFactory.class)
public interface CustomerHistoryRepository {
    @GetMapping("api/customer-history/{customerId}")
    CustomerHistoryModels findLatelyByCustomerId(@PathVariable long customerId, @SpringQueryMap Pageable pageable);
}
