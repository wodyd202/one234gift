package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterSalesUserService extends RegisterUserService{
    public RegisterSalesUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        super(userRepository, registerUserValidator, passwordEncoder, applicationEventPublisher);
    }

    @Override
    protected void afterRegister(User user) {
        log.info("save sales user {}", user);
    }

}
