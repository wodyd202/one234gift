package com.one234gift.calllistservice.call.command.application;

import com.one234gift.calllistservice.call.command.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.read.CallInfoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.calllistservice.call.command.domain.CallFixture.aReservatedCallEvent;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class RegisterReservationCallService_Test {
    @Autowired RegisterReservationCallService registerReservationCallService;
    @Autowired ReservationCallRepository reservationCallRepository;

    @Test
    void 예약콜_생성(){
        // given
        ReservatedCallEvent reservatedCallEvent = aReservatedCallEvent().build();

        // when
        CallInfoModel register = registerReservationCallService.register(reservatedCallEvent);

        // then
        assertTrue(reservationCallRepository.findById(register.getId()).isPresent());
    }
}
