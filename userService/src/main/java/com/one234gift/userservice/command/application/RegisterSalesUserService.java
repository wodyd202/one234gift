package com.one234gift.userservice.command.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RegisterSalesUserService extends RegisterUserService{
    public RegisterSalesUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator, PasswordEncoder passwordEncoder) {
        super(userRepository, registerUserValidator, passwordEncoder);
    }

    @Transactional
    public UserModel register(RegisterUser registerUser) {
        User user = User.registerSalesUser(registerUser);
        user.register(registerUserValidator, passwordEncoder);
        userRepository.save(user);
        log.info("save sales user {}", user);
        return user.toModel();
    }
}
