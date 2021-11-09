package com.one234gift.userservice;

import com.one234gift.userservice.command.application.RegisterUserService;
import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.value.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired private RegisterUserService registerUserService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RegisterUser registerUser = RegisterUser.builder()
                .phone("123-0000-0000")
                .username("경리담당자")
                .role(UserRole.ACCOUNTING_USER)
                .build();
        registerUserService.register(registerUser);
    }
}
