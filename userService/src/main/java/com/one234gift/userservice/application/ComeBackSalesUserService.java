package com.one234gift.userservice.application;

import com.one234gift.userservice.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.SalesUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.userservice.application.UserServiceHelper.findByPhone;

@Service
public class ComeBackSalesUserService {
    private final UserRepository userRepository;

    public ComeBackSalesUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void comeback(Phone phone) {
        User user = findByPhone(userRepository, phone);
        if(user instanceof SalesUser){
            user.comeBack();
        }else{
            throw new PhoneNotFoundException();
        }
    }
}
