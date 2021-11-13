package com.one234gift.calllistservice.call.command.application;

import com.one234gift.calllistservice.call.command.application.model.ChangedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ChangeReservationCallService {
    private ReservationCallRepository reservationCallRepository;

    public void changeWhen(ChangedCallEvent changedCallEvent) {
        ReservationCall reservationCall = reservationCallRepository.findBySalesHistoryId(new SalesHistoryId(changedCallEvent.getSalesHistoryId()));
        reservationCall.changeWhen(changedCallEvent.getCallReservationDate());
        log.info("change reservation call into database : {}", reservationCall.toModel());
    }
}
