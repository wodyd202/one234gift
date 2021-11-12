package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    boolean existsByPhone(Phone phone);
    Optional<User> findByPhone(Phone phone);
}
