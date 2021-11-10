package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.external.UserRepository;
import com.one234gift.customerservice.customer.domain.value.Manager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StubUserRepository implements UserRepository {
    private Manager manager;
    @Override
    public Optional<Manager> findUser(String userId) {
        return Optional.of(manager);
    }

    public void save(String userId){
        manager = new Manager("담당자명", userId);
    }
}
