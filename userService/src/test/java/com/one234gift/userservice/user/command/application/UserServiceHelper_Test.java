package com.one234gift.userservice.user.command.application;

import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.exception.UserNotFoundException;
import com.one234gift.userservice.domain.value.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;
import static com.one234gift.userservice.user.UserFixture.aUser;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class UserServiceHelper_Test {
    @Autowired
    UserRepository userRepository;

    @Test
    public void 사용자가_존재하지_않음(){
        Assertions.assertThrows(UserNotFoundException.class, ()->{
            findByPhone(userRepository, new Phone("000-0000-0000"));
        });
    }

    @Test
    public void 사용자가_존재(){
        userRepository.save(aUser("000-0000-1234"));
        assertNotNull(findByPhone(userRepository, new Phone("000-0000-1234")));
    }
}
