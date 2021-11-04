package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.external.UserRepository;
import com.one234gift.customerservice.domain.value.Manager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    private Manager manager;
    @Override
    public Optional<Manager> findUser() {
        return Optional.of(manager);
    }

    public void save(String userId){
        manager = new Manager("담당자명", userId);
    }
}
