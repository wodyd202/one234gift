package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.value.CustomerInfo;
import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Tests implements CustomerRepository, UserRepository{

    @Override
    public Optional<CustomerInfo> findById(long customerId) {
        System.out.println("이거 떠 ??");
        return Optional.empty();
    }

    @Override
    public Optional<SalesUser> findUser() {
        System.out.println("이거 뜸 ???");
        return Optional.empty();
    }
}
