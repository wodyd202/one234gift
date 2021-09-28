package com.one234gift.userservice.domain.model;

import com.one234gift.userservice.domain.value.State;
import lombok.Builder;

public class UserModel {
    private String username;
    private String phone;
    private State state;

    @Builder
    public UserModel(String username, String phone, State state) {
        this.username = username;
        this.phone = phone;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public State getState() {
        return state;
    }
}
