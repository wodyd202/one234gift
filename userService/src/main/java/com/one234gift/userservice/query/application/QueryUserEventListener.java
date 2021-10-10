package com.one234gift.userservice.query.application;

import com.one234gift.userservice.command.application.event.ComebackedUserEvent;
import com.one234gift.userservice.command.application.event.LeavedUserEvent;
import com.one234gift.userservice.command.application.event.RegisteredUserEvent;
import com.one234gift.userservice.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Async("userExecutor")
@Component
public class QueryUserEventListener {
    @Autowired private QueryUserRepository queryUserRepository;

    @EventListener
    public void handle(RegisteredUserEvent event){
        UserModel userModel = new UserModel(event);
        queryUserRepository.save(userModel);
    }

    @EventListener
    public void handle(LeavedUserEvent event){
        Optional<UserModel> userModel = queryUserRepository.findByPhone(event.getPhone());
        if(userModel.isPresent()){
            UserModel queryUserModel = userModel.get();
            queryUserModel.leave();
            queryUserRepository.save(queryUserModel);
        }
    }

    @EventListener
    public void handle(ComebackedUserEvent event){
        Optional<UserModel> userModel = queryUserRepository.findByPhone(event.getPhone());
        if(userModel.isPresent()){
            UserModel queryUserModel = userModel.get();
            queryUserModel.comeback();
            queryUserRepository.save(queryUserModel);
        }
    }
}
