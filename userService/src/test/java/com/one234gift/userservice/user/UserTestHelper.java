package com.one234gift.userservice.user;

import com.one234gift.userservice.application.UserRepository;
import com.one234gift.userservice.domain.User;

public class UserTestHelper {
    public static void persistUser(UserRepository userRepository, User user) {
        userRepository.save(user);
    }
}
