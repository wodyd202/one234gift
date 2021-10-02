package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.value.Manager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository{

    @Override
    public Optional<Manager> findUser() {
        return Optional.of(Manager.builder()
                        .name("담당자")
                        .phone("010-0000-0000")
                .build());
    }
}
