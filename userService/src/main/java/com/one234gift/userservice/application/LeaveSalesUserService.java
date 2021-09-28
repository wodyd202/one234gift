package com.one234gift.userservice.application;

import com.one234gift.userservice.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.SalesUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.userservice.application.UserServiceHelper.findByPhone;

@Service
public class LeaveSalesUserService {
    private final UserRepository userRepository;

    public LeaveSalesUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void leave(Phone phone){
        User user = findByPhone(userRepository, phone);
        if(user instanceof SalesUser){
            user.leave();
        }else{
            throw new PhoneNotFoundException();
        }
    }
}
