package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.application.HappyCallFactory;
import com.one234gift.happycallservice.application.StubCustomerRepository;
import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HappyCall_Test {
    HappyCallFactory factory = new HappyCallFactory(new StubCustomerRepository());

    @Test
    void 영업기록에_대한_예약콜_생성(){
        //given
        RegisterHappyCall registerHappyCall = RegisterHappyCall.builder()
                .customerId(1)
                .salesUser(new SalesUser("예약자"))
                .when(LocalDate.now().plusDays(1))
                .build();

        // when
        HappyCall happyCall = factory.newCallReservation(registerHappyCall);
        HappyCallModel happyCallModel = happyCall.toModel();

        // then
        assertTrue(happyCall instanceof CallReservation);
        assertEquals(happyCallModel.getSalesUser().getPhone(), "예약자");
        assertEquals(happyCallModel.getWhen(), LocalDate.now().plusDays(1));
    }

    @Test
    void 주문에_대한_예약콜_생성(){
        //given
        RegisterHappyCall registerHappyCall = RegisterHappyCall.builder()
                .customerId(1)
                .salesUser(new SalesUser("예약자"))
                .when(LocalDate.now().plusDays(1))
                .build();

        // when
        HappyCall happyCall = factory.newOrderHappyCall(registerHappyCall);
        HappyCallModel happyCallModel = happyCall.toModel();

        // then
        assertTrue(happyCall instanceof OrderHappyCall);
        assertEquals(happyCallModel.getSalesUser().getPhone(), "예약자");
        assertEquals(happyCallModel.getWhen(), LocalDate.now().plusDays(1));
    }
}
