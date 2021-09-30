package com.one234gift.userservice;

import com.one234gift.userservice.command.application.RegisterAccountingUserService;
import com.one234gift.userservice.domain.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired private RegisterAccountingUserService registerAccountingUserService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RegisterUser registerUser = RegisterUser.builder()
                .phone("123-0000-0000")
                .username("경리담당자")
                .build();
        registerAccountingUserService.register(registerUser);
    }
}
