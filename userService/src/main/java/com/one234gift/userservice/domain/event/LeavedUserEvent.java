package com.one234gift.userservice.domain.event;

import com.one234gift.userservice.domain.value.Phone;
import lombok.Getter;

/**
 * 사원 퇴사 이벤트
 */
@Getter
public class LeavedUserEvent extends AbstractUserEvent{
    public LeavedUserEvent(Phone phone) {
        super(phone.get());
    }
}
