package com.one234gift.userservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
abstract public class AbstractUserEvent {
    private final String phone;
}
