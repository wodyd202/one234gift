package com.one234gift.orderservice.command.application;

import com.one234gift.orderservice.domain.value.SalesUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    private SalesUser persistUser;

    @Override
    public Optional<SalesUser> findUser() {
        return Optional.of(persistUser);
    }

    public void save(String userId){
        persistUser = SalesUser.builder()
                .phone(userId)
                .name(userId)
                .build();
    }
}
