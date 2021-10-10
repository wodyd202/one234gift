package com.one234gift.userservice.command.application;

import com.one234gift.userservice.command.application.event.RegisteredUserEvent;
import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

abstract public class RegisterUserService {
    protected final UserRepository userRepository;
    protected final RegisterUserValidator registerUserValidator;
    protected final PasswordEncoder passwordEncoder;
    protected final ApplicationEventPublisher applicationEventPublisher;

    protected void afterRegister(User user){}

    public RegisterUserService(UserRepository userRepository,
                               RegisterUserValidator registerUserValidator,
                               PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.registerUserValidator = registerUserValidator;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public UserModel register(RegisterUser registerUser) {
        User user = User.registerSalesUser(registerUser);
        user.register(registerUserValidator, passwordEncoder);
        userRepository.save(user);
        afterRegister(user);
        UserModel userModel = user.toModel();
        applicationEventPublisher.publishEvent(new RegisteredUserEvent(userModel));
        return userModel;
    }
}
