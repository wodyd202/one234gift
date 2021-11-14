package com.one234gift.calllistservice.call.application;

import com.one234gift.calllistservice.call.application.model.*;
import com.one234gift.calllistservice.call.domain.ReservationCall;
import com.one234gift.calllistservice.call.domain.exception.ReservationCallNotFoundException;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.one234gift.calllistservice.call.domain.value.Reserver;
import com.one234gift.calllistservice.call.domain.value.SalesHistoryId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.calllistservice.call.application.ReservationCallServiceHelper.getReservationCall;

/**
 * 예약콜 등록 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ReservationCallService {
    private CallMapper callMapper;
    private ReservationCallRepository reservationCallRepository;

    // 조회 전용
    private QueryReservationCallRepository queryReservationCallRepository;

    /**
     * @param reservatedCallEvent
     * # 예약콜 생성
     */
    public ReservationCallModel register(ReservatedCallEvent reservatedCallEvent){
        ReservationCall reservationCall = callMapper.mapFrom(reservatedCallEvent);
        reservationCallRepository.save(reservationCall);

        ReservationCallModel callInfoModel = reservationCall.toModel();
        log.info("save reservation call into database : {}", callInfoModel);
        return callInfoModel;
    }

    /**
     * @param removedCallEvent
     * # 예약콜 제거
     */
    public void remove(RemovedCallEvent removedCallEvent) {
        reservationCallRepository.deleteBySalesHistoryId(new SalesHistoryId(removedCallEvent.getSalesHistoryId()));
        log.info("change reservation call into database : {}", removedCallEvent.getSalesHistoryId());
    }

    /**
     * @param changedCallEvent
     * # 예약콜 변경
     */
    public void changeWhen(ChangedCallEvent changedCallEvent) {
        ReservationCall reservationCall = getReservationCall(reservationCallRepository, new SalesHistoryId(changedCallEvent.getSalesHistoryId()));
        reservationCall.changeWhen(changedCallEvent.getCallReservationDate());
        log.info("change reservation call into database : {}", reservationCall.toModel());
    }

    /**
     * @param salesHistoryId
     * @param reserver
     * # 예약콜 읽음
     */
    public void read(SalesHistoryId salesHistoryId, Reserver reserver) {
        ReservationCall reservationCall = reservationCallRepository.findBySalesHistoryId(salesHistoryId).orElseThrow(ReservationCallNotFoundException::new);
        reservationCall.read(reserver);
        log.info("change reservation call into database : {}", reservationCall.toModel() );
    }

    /**
     * @param reservationCallSearchDTO
     * @param pageable
     * # 예약콜 리스트 조회
     */
    public ReservationCallModels getReservationCallModels(ReservationCallSearchDTO reservationCallSearchDTO, Pageable pageable) {
        return ReservationCallModels.builder()
                .reservationCallModels(queryReservationCallRepository.findAll(reservationCallSearchDTO, pageable))
                .totalElement(queryReservationCallRepository.countAll(reservationCallSearchDTO))
                .pageable(pageable)
                .build();
    }
}
