package com.one234gift.userservice.application;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RegisterSalesUserService {
    private final UserRepository userRepository;
    private final RegisterUserValidator registerUserValidator;

    public RegisterSalesUserService(UserRepository userRepository, RegisterUserValidator registerUserValidator) {
        this.userRepository = userRepository;
        this.registerUserValidator = registerUserValidator;
    }

    @Transactional
    public UserModel register(RegisterUser registerUser) {
        User user = User.registerSalesUser(registerUser);
        user.register(registerUserValidator);
        userRepository.save(user);
        log.info("save sales user {}", user);
        return user.toModel();
    }
}
