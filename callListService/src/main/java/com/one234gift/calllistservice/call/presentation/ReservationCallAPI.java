package com.one234gift.calllistservice.call.presentation;

import com.one234gift.calllistservice.call.application.ReservationCallService;
import com.one234gift.calllistservice.call.application.model.Pageable;
import com.one234gift.calllistservice.call.application.model.ReservationCallModels;
import com.one234gift.calllistservice.call.application.model.ReservationCallSearchDTO;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 예약콜 API
 */
@RestController
@RequestMapping("api/reservation-call")
public class ReservationCallAPI {
    @Autowired private ReservationCallService reservationCallService;

    /**
     * @param reservationCallSearchDTO
     * @param pageable
     * # 예약콜 리스트 조회
     */
    @GetMapping
    public ResponseEntity<ReservationCallModels> getReservationCallModels(ReservationCallSearchDTO reservationCallSearchDTO, Pageable pageable){
        ReservationCallModels reservationCallModels = reservationCallService.getReservationCallModels(reservationCallSearchDTO, pageable);
        return ResponseEntity.ok(reservationCallModels);
    }

    /**
     * @param salesHistoryId
     * @param principal
     * # 예약콜 읽음
     */
    @GetMapping("{salesHistoryId}")
    public ResponseEntity<Void> read(@PathVariable SalesHistoryId salesHistoryId, Principal principal){
        reservationCallService.read(salesHistoryId, new Reserver(principal.getName()));
        return ResponseEntity.ok(null);
    }

}
