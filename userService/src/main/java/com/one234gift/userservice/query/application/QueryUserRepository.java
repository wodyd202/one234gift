package com.one234gift.userservice.query.application;

import com.one234gift.userservice.domain.model.UserModel;

import java.util.Optional;

public interface QueryUserRepository {
    Optional<UserModel> findByPhone(String phone);
    void save(UserModel userModel);
}
