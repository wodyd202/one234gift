package com.one234gift.orderservice.order.command.application;

import com.one234gift.orderservice.order.command.application.util.ProcessUserIdGetter;
import org.springframework.stereotype.Component;

@Component
public class StubUserIdGetter implements ProcessUserIdGetter {
    @Override
    public String get() {
        return "userId";
    }
}
