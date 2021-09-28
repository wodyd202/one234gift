package com.one234gift.userservice.application;

import com.one234gift.userservice.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;

public class UserServiceHelper {
    public static User findByPhone(UserRepository userRepository, Phone phone) {
        return userRepository.findByPhone(phone).orElseThrow(PhoneNotFoundException::new);
    }
}
