package com.one234gift.userservice.user;

import com.one234gift.userservice.APITest;
import com.one234gift.userservice.command.application.UserRepository;
import com.one234gift.userservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAPITest extends APITest {
    @Autowired protected UserRepository userRepository;

    public void persistUser(User user) {
        if(!userRepository.existsByPhone(user.getPhone())){
            userRepository.save(user);
        }
    }
}
