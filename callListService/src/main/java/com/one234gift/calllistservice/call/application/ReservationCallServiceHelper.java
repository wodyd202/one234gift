package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.exception.ReservationCallNotFoundException;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;

public class ReservationCallServiceHelper {
    public static ReservationCall getReservationCall(ReservationCallRepository reservationCallRepository, SalesHistoryId salesHistoryId) {
        return reservationCallRepository.findBySalesHistoryId(salesHistoryId).orElseThrow(ReservationCallNotFoundException::new);
    }
}
