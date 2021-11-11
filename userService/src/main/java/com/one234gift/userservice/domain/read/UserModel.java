package com.one234gift.userservice.domain.read;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one234gift.userservice.command.event.ComebackedUserEvent;
import com.one234gift.userservice.command.event.LeavedUserEvent;
import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.domain.value.UserState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModel {
    private String username;
    private String phone;
    private String password;
    private UserState state;
    private UserRole role;

    protected UserModel(){}

    @Builder
    public UserModel(String username, String phone, String password, UserState state, UserRole role) {
        this.username = username;
        this.phone = phone;
        this.state = state;
        this.role = role;
        this.password = password;
    }

    protected void on(ComebackedUserEvent event){
        state = UserState.WORK;
    }

    protected void on(LeavedUserEvent event){
        state = UserState.LEAVE;
    }

    @JsonIgnore
    public boolean isLeaved() {
        return state == UserState.LEAVE;
    }

    /**
     * @param requesterInfo
     * # 다른 사원의 정보를 조회할 수 있는가 체크
     */
    @JsonIgnore
    public boolean isReadAble(UserModel requesterInfo) {
        return requesterInfo.role.equals(UserRole.ACCOUNTING_USER) || requesterInfo.phone.equals(phone);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", role=" + role +
                '}';
    }
}
