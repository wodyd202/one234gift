package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.external.UserRepository;
import com.one234gift.orderservice.order.domain.value.SalesUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    private SalesUser persistUser;

    @Override
    public Optional<SalesUser> findUser(String userId) {
        return Optional.of(persistUser);
    }

    public void save(String userId){
        persistUser = SalesUser.builder()
                .phone(userId)
                .name("영업사원")
                .build();
    }
}
