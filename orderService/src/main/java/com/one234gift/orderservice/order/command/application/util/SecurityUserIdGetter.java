package com.one234gift.orderservice.order.command.application.util;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class SecurityUserIdGetter implements ProcessUserIdGetter {

    @Override
    public String get() {
        return getAuthentication().getName();
    }

    private static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private static String getRequesterPhone(){
        return getAuthentication().getName();
    }

}
