package com.one234gift.userservice.user.command.application;

import com.one234gift.userservice.command.application.RegisterUserService;
import com.one234gift.userservice.command.application.model.RegisterUser;
import com.one234gift.userservice.domain.exception.AlreadyExistUserException;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.user.UserAPITest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.userservice.user.UserFixture.aRegisterUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 사용자 등록 테스트
 */
public class RegisterUserService_Test extends UserAPITest {
    @Autowired
    RegisterUserService registerUserService;

    @Test
    void 경리_담당자_생성(){
        // given
        RegisterUser registerUser = aRegisterUser().phone("010-0000-0000").role(UserRole.ACCOUNTING_USER).build();

        // when
        UserModel userModel = registerUserService.register(registerUser);

        // then
        assertEquals(userModel.getRole(), UserRole.ACCOUNTING_USER);
    }

    @Test
    void 영업_담당자_생성(){
        // given
        RegisterUser registerUser = aRegisterUser().phone("010-0000-0001").role(UserRole.SALES_USER).build();

        // when
        UserModel userModel = registerUserService.register(registerUser);

        // then
        assertEquals(userModel.getRole(), UserRole.SALES_USER);
    }

    @Test
    void 중복된_아이디는_허용하지_않음(){
        // given
        RegisterUser registerUser = aRegisterUser().phone("010-0000-0002").role(UserRole.ACCOUNTING_USER).build();
        registerUserService.register(registerUser);

        // when
        assertThrows(AlreadyExistUserException.class,()->{
            registerUserService.register(registerUser);
        });
    }
}
