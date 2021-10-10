package com.one234gift.userservice.command.application;

import com.one234gift.userservice.command.application.event.ComebackedUserEvent;
import com.one234gift.userservice.command.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.SalesUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;

@Service
public class ComeBackSalesUserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ComeBackSalesUserService(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void comeback(Phone phone) {
        User user = findByPhone(userRepository, phone);
        if(user instanceof SalesUser){
            user.comeBack();
            applicationEventPublisher.publishEvent(new ComebackedUserEvent(phone));
        }else{
            throw new PhoneNotFoundException();
        }
    }
}
