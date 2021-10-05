package com.one234gift.orderservice.application;

import com.one234gift.orderservice.domain.value.SalesUser;

import java.util.Optional;

public interface UserRepository {
    Optional<SalesUser> findUser();
}
