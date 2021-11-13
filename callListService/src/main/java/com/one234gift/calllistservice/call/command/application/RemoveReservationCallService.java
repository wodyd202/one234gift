package com.one234gift.calllistservice.call.command.application;

import com.one234gift.calllistservice.call.command.application.model.RemovedCallEvent;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RemoveReservationCallService {
    private ReservationCallRepository reservationCallRepository;

    public void remove(RemovedCallEvent removedCallEvent) {
        reservationCallRepository.deleteBySalesHistoryId(new SalesHistoryId(removedCallEvent.getSalesHistoryId()));
        log.info("change reservation call into database : {}", removedCallEvent.getSalesHistoryId());
    }
}
