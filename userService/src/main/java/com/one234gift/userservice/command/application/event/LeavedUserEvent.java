package com.one234gift.userservice.command.application.event;

import com.one234gift.userservice.domain.value.Phone;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LeavedUserEvent {
    private String phone;
    public LeavedUserEvent(Phone phone) {
        this.phone = phone.get();
    }
}
