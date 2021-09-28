package com.one234gift.userservice.application;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;

public interface UserRepository {
    void save(User user);
    boolean existByPhone(Phone phone);
}
