package com.one234gift.userservice.domain.event;

import com.one234gift.userservice.domain.value.Phone;
import lombok.Getter;

/**
 * 사원 재입사 이벤트
 */
@Getter
public class ComebackedUserEvent extends AbstractUserEvent{
    public ComebackedUserEvent(Phone phone) {
        super(phone.get());
    }
}
