package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationCallRepository extends JpaRepository<ReservationCall, Long> {
    Optional<ReservationCall> findBySalesHistoryId(SalesHistoryId salesHistoryId);
    void deleteBySalesHistoryId(SalesHistoryId salesHistoryId);
}
