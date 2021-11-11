package com.one234gift.userservice.command.event;

import com.one234gift.userservice.domain.value.UserRole;
import com.one234gift.userservice.domain.value.Password;
import com.one234gift.userservice.domain.value.Phone;
import com.one234gift.userservice.domain.value.UserState;
import com.one234gift.userservice.domain.value.Username;
import lombok.Getter;

/**
 * 사원 등록 이벤트
 */
@Getter
public class RegisteredUserEvent {
    private String phone, username, password;
    private UserRole role;
    private UserState state;

    public RegisteredUserEvent(Phone phone,
                               Username username,
                               Password password,
                               UserRole role) {
        this.phone = phone.get();
        this.username = username.get();
        this.role = role;
        this.state = UserState.WORK;
        this.password = password.get();
    }
}
