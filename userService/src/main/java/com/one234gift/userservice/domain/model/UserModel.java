package com.one234gift.userservice.domain.model;

import com.one234gift.userservice.command.application.event.RegisteredUserEvent;
import com.one234gift.userservice.domain.value.State;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModel {
    private String username;
    private String phone;
    private String password;
    private State state;
    private String role;

    protected UserModel(){}

    @Builder
    public UserModel(String username, String phone, String password,State state, String role) {
        this.username = username;
        this.phone = phone;
        this.state = state;
        this.role = role;
        this.password = password;
    }

    public UserModel(RegisteredUserEvent event) {
        this.username = event.getUsername();
        this.phone = event.getPhone();
        this.state = event.getState();
        this.role = event.getRole();
        this.password = event.getPassword();
    }

    public void leave() {
        this.state = State.LEAVE;
    }

    public void comeback() {
        this.state = State.WORK;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", role='" + role + '\'' +
                '}';
    }
}
