package com.one234gift.userservice.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAccountingUserService {
    private final UserRepository userRepository;
    private final RegisterUserValidator registerUserValidator;

    public RegisterAccountingUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator) {
        this.userRepository = userRepository;
        this.registerUserValidator = registerUserValidator;
    }

    @Transactional
    public UserModel register(RegisterUser registerUser) {
        User user = User.registerAccountingUser(registerUser);
        user.register(registerUserValidator);
        userRepository.save(user);
        return user.toModel();
    }
}
