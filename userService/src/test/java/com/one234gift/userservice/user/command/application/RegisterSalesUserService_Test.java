package com.one234gift.userservice.user.command.application;

import com.one234gift.userservice.command.application.exception.DuplicatePhoneException;
import com.one234gift.userservice.command.application.RegisterSalesUserService;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;
import com.one234gift.userservice.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static com.one234gift.userservice.user.UserFixture.aSalesUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RegisterSalesUserService_Test {
    @Autowired
    RegisterSalesUserService registerSalesUserService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 영업사원_등록(){
        RegisterUser registerUser = RegisterUser.builder()
                .username("영업사원")
                .phone("010-0000-0000")
                .build();
        UserModel userModel = registerSalesUserService.register(registerUser);
        assertNotNull(userModel);
    }

    @Test
    void 중복된_휴대폰(){
        User user = aSalesUser("000-1345-0000");
        userRepository.save(user);

        assertThrows(DuplicatePhoneException.class,()->{
            RegisterUser duplicateUser = aRegisterUser()
                    .phone("000-1345-0000")
                    .build();
            registerSalesUserService.register(duplicateUser);
        });
    }
}
