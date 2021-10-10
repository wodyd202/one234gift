package com.one234gift.userservice.command.application;

import com.one234gift.userservice.command.application.event.LeavedUserEvent;
import com.one234gift.userservice.command.application.exception.PhoneNotFoundException;
import com.one234gift.userservice.domain.SalesUser;
import com.one234gift.userservice.domain.User;
import com.one234gift.userservice.domain.value.Phone;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.userservice.command.application.UserServiceHelper.findByPhone;

@Service
public class LeaveSalesUserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public LeaveSalesUserService(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void leave(Phone phone){
        User user = findByPhone(userRepository, phone);
        if(user instanceof SalesUser){
            user.leave();
            applicationEventPublisher.publishEvent(new LeavedUserEvent(phone));
        }else{
            throw new PhoneNotFoundException();
        }
    }
}
