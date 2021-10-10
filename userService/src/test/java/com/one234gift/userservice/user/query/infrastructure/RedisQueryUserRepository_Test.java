package com.one234gift.userservice.user.query.infrastructure;

import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.State;
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
                .role("role")
                .state(State.LEAVE)
                .phone("phone")
                .username("username")
                .build();
        redisQueryUserRepository.save(userModel);
        userModel = redisQueryUserRepository.findByPhone("phone").get();
        assertEquals(userModel.getUsername(), "username");
        assertEquals(userModel.getPassword(), "password");
        assertEquals(userModel.getState(), State.LEAVE);
        assertEquals(userModel.getPhone(), "phone");
        assertEquals(userModel.getRole(), "role");
    }
}
