package com.one234gift.calllistservice.call.infrastructure;

import com.one234gift.calllistservice.call.application.ReservationCallRepository;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.exception.ReservationCallNotFoundException;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.one234gift.calllistservice.call.application.ReservationCallServiceHelper.getReservationCall;
import static com.one234gift.calllistservice.call.domain.CallFixture.aReservationCall;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CallRepository_Test {
    @Autowired ReservationCallRepository reservationCallRepository;

    @Test
    void 예약콜_저장(){
        // given
        ReservationCall reservationCall = aReservationCall().salesHistoryId(new SalesHistoryId("salesHistoryId")).build();

        // when
        reservationCallRepository.save(reservationCall);
        Optional<ReservationCall> persistReservationCall = reservationCallRepository.findBySalesHistoryId(new SalesHistoryId("salesHistoryId"));

        // then
        assertTrue(persistReservationCall.isPresent());
    }

    @Test
    void 영업기록_고유값으로_예약콜_조회(){
        // given
        SalesHistoryId salesHistoryId = new SalesHistoryId("salesHistoryId");

        // when
        assertDoesNotThrow(()->{
            reservationCallRepository.findBySalesHistoryId(salesHistoryId);
        });
    }

    @Test
    void 예약콜_삭제(){
        // given
        ReservationCall reservationCall = aReservationCall().salesHistoryId(new SalesHistoryId("salesHistoryId")).build();
        reservationCallRepository.save(reservationCall);
        ReservationCallModel callInfoModel = reservationCall.toModel();

        // when
        reservationCallRepository.deleteBySalesHistoryId(new SalesHistoryId("salesHistoryId"));

        // then
        assertThrows(ReservationCallNotFoundException.class, ()->{
            getReservationCall(reservationCallRepository, new SalesHistoryId("salesHistoryId"));
        });
    }
}
