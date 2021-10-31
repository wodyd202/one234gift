package com.one234gift.happycallservice;

import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import com.one234gift.happycallservice.domain.model.SalesUser;

import java.time.LocalDate;

public class HappyCallFixture {

    public static RegisterHappyCall.RegisterHappyCallBuilder aRegisterHappyCall() {
        return RegisterHappyCall.builder()
                .customerId(1)
                .salesUser(new SalesUser("예약자"))
                .when(LocalDate.now().plusDays(1));
    }
}
