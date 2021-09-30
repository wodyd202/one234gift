package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUserService {
    protected final UserRepository userRepository;
    protected final RegisterUserValidator registerUserValidator;
    protected final PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.registerUserValidator = registerUserValidator;
        this.passwordEncoder = passwordEncoder;
    }
}
