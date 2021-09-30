package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAccountingUserService extends RegisterUserService{
    public RegisterAccountingUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator, PasswordEncoder passwordEncoder) {
        super(userRepository, registerUserValidator, passwordEncoder);
    }

    @Transactional
    public UserModel register(RegisterUser registerUser) {
        User user = User.registerAccountingUser(registerUser);
        user.register(registerUserValidator, passwordEncoder);
        userRepository.save(user);
        return user.toModel();
    }
}
