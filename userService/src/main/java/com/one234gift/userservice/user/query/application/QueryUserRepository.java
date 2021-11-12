package com.one234gift.userservice.query.application;

import com.one234gift.userservice.domain.read.UserModel;

import java.util.Optional;

public interface QueryUserRepository {
    Optional<UserModel> findByPhone(String phone);
    void save(UserModel userModel);
}
