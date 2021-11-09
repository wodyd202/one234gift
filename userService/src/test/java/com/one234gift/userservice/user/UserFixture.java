package com.one234gift.userservice.user;

import com.one234gift.userservice.command.application.UserMapper;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.command.application.model.RegisterUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserFixture {
    private static UserMapper userMapper = new UserMapper();
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User aUser(String phone){
        User salesUser = userMapper.mapFrom(RegisterUser.builder()
                .username("영업사원")
                .phone(phone)
                .role(UserRole.SALES_USER)
                .build(), passwordEncoder);
        return salesUser;
    }

    public static User leavedUser(String phone){
        User user = aUser(phone);
        user.leave();
        return user;
    }

    public static RegisterUser.RegisterUserBuilder aRegisterUser(){
        return RegisterUser
                .builder()
                .phone("000-0000-0000")
                .username("사용자")
                .role(UserRole.SALES_USER);
    }
}
