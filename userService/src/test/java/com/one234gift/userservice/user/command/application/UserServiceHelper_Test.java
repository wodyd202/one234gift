package com.one234gift.userservice.user.command.application;

import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.command.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.user.UserFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceHelper_Test {
    @Autowired
    UserRepository userRepository;

    @Test(expected = PhoneNotFoundException.class)
    public void 사용자가_존재하지_않음(){
        findByPhone(userRepository, new Phone("000-0000-0000"));
    }

    @Test
    public void 사용자가_존재(){
        userRepository.save(UserFixture.aSalesUser("000-0000-1234"));
        assertNotNull(findByPhone(userRepository, new Phone("000-0000-1234")));
    }
}
