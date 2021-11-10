package com.one234gift.userservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract public class AbstractUserEvent {
    private final String phone;
}
