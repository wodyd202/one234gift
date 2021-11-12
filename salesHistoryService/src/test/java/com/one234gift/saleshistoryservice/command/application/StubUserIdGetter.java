package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.util.ProcessUserIdGetter;
import org.springframework.stereotype.Component;

@Component
public class StubUserIdGetter implements ProcessUserIdGetter {
    @Override
    public String get() {
        return "userId";
    }
}
