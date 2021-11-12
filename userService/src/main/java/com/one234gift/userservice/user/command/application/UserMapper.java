package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * @param registerUser
     * # registerUser to user
     * @param passwordEncoder
     */
    public User mapFrom(RegisterUser registerUser, PasswordEncoder passwordEncoder) {
        return User.builder()
                .phone(new Phone(registerUser.getPhone()))
                .name(new Username(registerUser.getUsername()))
                .passwordEncoder(passwordEncoder)
                .role(registerUser.getRole())
                .build();
    }
}
