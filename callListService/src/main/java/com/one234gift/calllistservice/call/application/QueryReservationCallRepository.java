package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.application.model.Pageable;
import com.one234gift.calllistservice.call.application.model.ReservationCallSearchDTO;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;

import java.util.List;

public interface QueryReservationCallRepository {
    List<ReservationCallModel> findAll(ReservationCallSearchDTO reservationCallSearchDTO, Pageable pageable);
    long countAll(ReservationCallSearchDTO reservationCallSearchDTO);
}
