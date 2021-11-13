package com.one234gift.calllistservice.call.command.application;

import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationCallRepository extends JpaRepository<ReservationCall, Long> {
    ReservationCall findBySalesHistoryId(SalesHistoryId salesHistoryId);
    void deleteBySalesHistoryId(SalesHistoryId salesHistoryId);
}
