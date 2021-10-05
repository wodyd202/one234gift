package com.one234gift.orderservice.application;

import com.one234gift.orderservice.command.application.UserRepository;
import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    @Override
    public Optional<SalesUser> findUser() {
        return Optional.of(SalesUser.builder()
                        .name("영업자")
                        .phone("000-0000-0000")
                .build());
    }
}
