package com.one234gift.userservice.user.infrastructure;

import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.query.application.QueryUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.user.UserFixture.aSalesUser;

@SpringBootTest
public class QueryUserRepository_Test {
    @Autowired
    QueryUserRepository queryUserRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void accessToken(){
        User salesUser = aSalesUser("010-1234-0000");
        userRepository.save(salesUser);
        UserModel userModel = queryUserRepository.findByPhone("010-1234-0000").get();
        System.out.println(userModel);
    }
}
