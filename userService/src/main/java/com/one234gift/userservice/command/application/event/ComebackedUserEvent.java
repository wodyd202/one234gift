package com.one234gift.userservice.command.application.event;

import com.one234gift.userservice.domain.value.Phone;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ComebackedUserEvent {
    private String phone;
    public ComebackedUserEvent(Phone phone) {
        this.phone = phone.get();
    }
}
