package com.one234gift.userservice.command.application.event;

import com.one234gift.userservice.domain.model.UserModel;
import com.one234gift.userservice.domain.value.State;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisteredUserEvent {
    private String phone, username, password, role;
    private State state;

    @Builder
    public RegisteredUserEvent(UserModel userModel) {
        this.phone = userModel.getPhone();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.state = userModel.getState();
        this.role = userModel.getRole();
    }
}
