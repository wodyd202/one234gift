package com.one234gift.calllistservice.call.command.application;

import com.one234gift.calllistservice.call.command.application.model.ReservatedCallEvent;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.read.CallInfoModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예약콜 등록 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RegisterReservationCallService {
    private CallMapper callMapper;
    private ReservationCallRepository reservationCallRepository;

    public CallInfoModel register(ReservatedCallEvent reservatedCallEvent){
        ReservationCall reservationCall = callMapper.mapFrom(reservatedCallEvent);
        reservationCallRepository.save(reservationCall);

        CallInfoModel callInfoModel = reservationCall.toModel();
        log.info("save reservation call into database : {}", callInfoModel);
        return callInfoModel;
    }
}
