package com.one234gift.userservice.domain.model;

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

    @Builder
    public UserModel(String username, String phone, String password,State state, String role) {
        this.username = username;
        this.phone = phone;
        this.state = state;
        this.role = role;
        this.password = password;
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
