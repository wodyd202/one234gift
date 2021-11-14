package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.exception.ReservationCallNotFoundException;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.one234gift.calllistservice.call.application.ReservationCallServiceHelper.getReservationCall;
import static com.one234gift.calllistservice.call.domain.CallFixture.aReservatedCallEvent;
import static com.one234gift.calllistservice.call.domain.CallFixture.aReservationCall;
import static org.junit.Assert.*;

@SpringBootTest
public class ReservationCallService_Test {
    @Autowired ReservationCallService reservationCallService;
    @Autowired ReservationCallRepository reservationCallRepository;

    @Test
    void 예약콜_조회(){
        // given
        reservationCallRepository.save(aReservationCall().salesHistoryId(new SalesHistoryId("salesHistoryId")).build());

        // when
        ReservationCall reservationCall = getReservationCall(reservationCallRepository, new SalesHistoryId("salesHistoryId"));

        // then
        assertNotNull(reservationCall);
    }

    @Test
    void 예약콜_조회시_해당_예약콜이_존재하지_않으면_에러발생(){
        assertThrows(ReservationCallNotFoundException.class, ()->{
            getReservationCall(reservationCallRepository, new SalesHistoryId("notFound"));
        });
    }

    @Test
    void 예약콜_생성(){
        // given
        ReservatedCallEvent reservatedCallEvent = aReservatedCallEvent().salesHistoryId("salesHistoryId").build();

        // when
        ReservationCallModel register = reservationCallService.register(reservatedCallEvent);

//         then
        assertTrue(reservationCallRepository.findBySalesHistoryId(new SalesHistoryId("salesHistoryId")).isPresent());
    }

    @Test
    void 예약콜_읽음(){
        // given
        reservationCallRepository.save(aReservationCall()
                                .salesHistoryId(new SalesHistoryId("salesHistoryId"))
                                .when(LocalDate.now())
                                .reserver(new Reserver("reserver"))
                                .build());

        // when
        reservationCallService.read(new SalesHistoryId("salesHistoryId"), new Reserver("reserver"));
        ReservationCallModel callInfoModel = reservationCallRepository.findBySalesHistoryId(new SalesHistoryId("salesHistoryId")).get().toModel();

        // then
        assertTrue(callInfoModel.isRead());
    }
}
