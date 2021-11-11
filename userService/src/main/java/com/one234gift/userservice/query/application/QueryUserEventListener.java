package com.one234gift.userservice.query.application;

import com.one234gift.userservice.command.event.AbstractUserEvent;
import com.one234gift.userservice.command.event.RegisteredUserEvent;
import com.one234gift.userservice.domain.read.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 사원 이벤트 리스너
 */
@Async("queryUserExecutor")
@Component
@Slf4j
@Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 500))
public class QueryUserEventListener {
    @Autowired private QueryUserRepository queryUserRepository;

    @EventListener
    public void handle(RegisteredUserEvent event){
        UserModel userModel = UserModel.builder()
                .username(event.getUsername())
                .phone(event.getPhone())
                .password(event.getPassword())
                .state(event.getState())
                .role(event.getRole())
                .build();
        queryUserRepository.save(userModel);
        log.info("save user into redis store : {}", userModel);
    }

    @EventListener
    public void handle(AbstractUserEvent event) throws Exception {
        Optional<UserModel> userModel = queryUserRepository.findByPhone(event.getPhone());
        if(userModel.isPresent()){
            UserModel queryUserModel = userModel.get();
            invoke(event, queryUserModel);
            queryUserRepository.save(queryUserModel);
            log.info("change user into redis store : {}", queryUserModel);
        }
    }

    private final String ON = "on";
    private void invoke(AbstractUserEvent event, UserModel queryUserModel) throws Exception {
        Method on = queryUserModel.getClass().getDeclaredMethod(ON, event.getClass());
        on.setAccessible(true);
        on.invoke(queryUserModel, event);
    }
}
