package com.one234gift.calllistservice.call.command.infrastructure;

import com.one234gift.calllistservice.call.command.application.ReservationCallRepository;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.read.CallInfoModel;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.one234gift.calllistservice.call.command.domain.CallFixture.aReservationCall;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CallRepository_Test {
    @Autowired
    ReservationCallRepository reservationCallRepository;

    @Test
    void 예약콜_저장(){
        // given
        ReservationCall reservationCall = aReservationCall().build();

        // when
        reservationCallRepository.save(reservationCall);
        CallInfoModel callInfoModel = reservationCall.toModel();
        Optional<ReservationCall> byId = reservationCallRepository.findById(callInfoModel.getId());

        // then
        assertTrue(byId.isPresent());
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
        ReservationCall reservationCall = aReservationCall().build();
        reservationCallRepository.save(reservationCall);
        CallInfoModel callInfoModel = reservationCall.toModel();

        // when
        reservationCallRepository.deleteBySalesHistoryId(new SalesHistoryId(callInfoModel.getSalesHistoryId()));

        // then
        ReservationCall reservationCallRepositoryBySalesHistoryId = reservationCallRepository.findBySalesHistoryId(new SalesHistoryId(callInfoModel.getSalesHistoryId()));
        assertNull(reservationCallRepositoryBySalesHistoryId);
    }
}
