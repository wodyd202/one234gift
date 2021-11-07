package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.read.CallReservationModel;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.read.OrderHappyCallModel;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.happycallservice.HappyCallFixture.aRegisterHappyCall;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class HappyCallService_Test {
    @Autowired
    HappyCallService happyCallService;

    @Test
    void 영업_기록에_대한_해피콜_등록(){
        // given
        RegisterHappyCall registerHappyCall = aRegisterHappyCall().build();

        // when
        HappyCallModel happyCallModel = happyCallService.registerCallReservation(registerHappyCall);

        // then
        assertTrue(happyCallModel instanceof CallReservationModel);
    }

    @Test
    void 주문_예약콜_등록(){
        // given
        RegisterHappyCall registerHappyCall = aRegisterHappyCall().build();

        // when
        HappyCallModel happyCallModel = happyCallService.registerOrderHappyCall(registerHappyCall);

        // then
        assertTrue(happyCallModel instanceof OrderHappyCallModel);
    }

    @Test
    void 읽음() {
        // given
        RegisterHappyCall registerHappyCall = aRegisterHappyCall().writer(new SalesUser("담당자")).build();
        HappyCallModel happyCallModel = happyCallService.registerOrderHappyCall(registerHappyCall);

        // when
        happyCallService.read(happyCallModel.getSeq(), new SalesUserInfo("담당자"));
    }

}
