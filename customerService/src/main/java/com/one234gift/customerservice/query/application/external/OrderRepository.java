package com.one234gift.customerservice.query.application.external;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDER-SERVICE", configuration = FeignClientConfig.class, fallbackFactory = CustomerHistoryFallbackFactory.class)
public interface OrderRepository {
    @GetMapping("api/order/customer/{customerId}")
    OrderModels findByCustomerId(@PathVariable Long customerId, @SpringQueryMap Pageable pageable);
}
