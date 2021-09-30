package com.one234gift.userservice.user.application;

import com.one234gift.userservice.command.application.RegisterAccountingUserService;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegisterAccountingUserService_Test {
    @Autowired
    RegisterAccountingUserService registerAccountingUserService;

    @Test
    void 경리_담당자_생성(){
        RegisterUser registerUser = aRegisterUser().build();

        UserModel userModel = registerAccountingUserService.register(registerUser);
        assertNotNull(userModel);
    }
}
