package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.external.CustomerRepository;
import com.one234gift.orderservice.order.domain.value.CustomerInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubCustomerRepository implements CustomerRepository {
    @Override
    public Optional<CustomerInfo> findById(long customerId) {
        return Optional.of(
                CustomerInfo.builder()
                        .id(customerId)
                        .name("업체명")
                        .category("은행")
                        .build()
        );
    }
}
