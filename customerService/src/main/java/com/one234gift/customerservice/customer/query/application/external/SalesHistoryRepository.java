package com.one234gift.customerservice.customer.query.application.external;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SALESHISTORY-SERVICE",
             configuration = FeignClientConfig.class,
             fallbackFactory = SalesHistoryRepositoryFallbackFactory.class)
public interface SalesHistoryRepository {
    @GetMapping("api/sales-history/customer/{customerId}")
    SalesHistoryModels findLatelyByCustomerId(@PathVariable long customerId, @SpringQueryMap Pageable pageable);
}
