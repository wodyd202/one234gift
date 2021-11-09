package com.one234gift.userservice.user.query.infrastructure;

import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.domain.read.UserModel;
import com.one234gift.userservice.domain.value.UserState;
import com.one234gift.userservice.query.infrastructure.RedisQueryUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RedisQueryUserRepository_Test {
    @Autowired
    RedisQueryUserRepository redisQueryUserRepository;

    @Test
    void save(){
        UserModel userModel = UserModel.builder()
                .password("password")
                .role(UserRole.SALES_USER)
                .state(UserState.LEAVE)
                .phone("phone")
                .username("username")
                .build();
        redisQueryUserRepository.save(userModel);
        userModel = redisQueryUserRepository.findByPhone("phone").get();
        assertEquals(userModel.getUsername(), "username");
        assertEquals(userModel.getPassword(), "password");
        assertEquals(userModel.getState(), UserState.LEAVE);
        assertEquals(userModel.getPhone(), "phone");
        assertEquals(userModel.getRole(), UserRole.SALES_USER);
    }
}
