package com.one234gift.userservice.user;

import com.one234gift.userservice.domain.RegisterUserValidator;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.RegisterUser;

import static org.mockito.Mockito.mock;

public class UserFixture {
    public static User aSalesUser(String phone){
        User salesUser = User.registerSalesUser(RegisterUser.builder()
                .username("영업사원")
                .phone(phone)
                .build());
        salesUser.register(mock(RegisterUserValidator.class));
        return salesUser;
    }

    public static RegisterUser.RegisterUserBuilder aRegisterUser(){
        return RegisterUser
                .builder()
                .phone("000-0000-0000")
                .username("사용자");
    }
}
