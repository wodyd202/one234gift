package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.domain.value.CustomerInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubCustomerRepository implements CustomerRepository{
    @Override
    public Optional<CustomerInfo> findById(long customerId) {
        return Optional.of(CustomerInfo.builder()
                        .category("은행")
                        .name("은행명")
                        .id(customerId)
                .build());
    }
}
