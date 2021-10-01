package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.value.Manager;

import java.util.Optional;

public interface UserRepository {
    Optional<Manager> findById(String userId);
}
