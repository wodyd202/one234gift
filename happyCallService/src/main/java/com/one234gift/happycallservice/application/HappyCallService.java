package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.domain.exception.HappyCallNotFoundException;
import com.one234gift.happycallservice.common.Pageable;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.model.RegisterHappyCall;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import com.one234gift.happycallservice.application.model.HappyCallModels;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예약콜 서비스
 */
@Slf4j
@Service
@AllArgsConstructor
public class HappyCallService {
    private HappyCallRepository happyCallRepository;
    private HappyCallFactory happyCallFactory;

    /**
     * @param registerHappyCall
     * # 주문에 대한 예약콜 생성
     */
    @Transactional
    public HappyCallModel registerOrderHappyCall(RegisterHappyCall registerHappyCall) {
        HappyCallModel happyCallModel = registerHappyCall(happyCallFactory.newOrderHappyCall(registerHappyCall));
        log.info("save order happy call : {}", happyCallModel);
        return happyCallModel;
    }

    /**
     * @param registerHappyCall
     * # 영업 기록에 대한 예약콜 생성
     */
    @Transactional
    public HappyCallModel registerCallReservation(RegisterHappyCall registerHappyCall) {
        HappyCallModel happyCallModel = registerHappyCall(happyCallFactory.newCallReservation(registerHappyCall));
        log.info("save call reservation : {}", happyCallModel);
        return happyCallModel;
    }

    private HappyCallModel registerHappyCall(HappyCall happyCall){
        happyCallRepository.save(happyCall);
        return happyCall.toModel();
    }

    /**
     * @param happyCallId
     * @param salesUser
     * # 해피콜 읽음
     */
    @Transactional
    public void read(long happyCallId, SalesUserInfo salesUser){
        HappyCall happyCall = happyCallRepository.findByIdAndSalesUser(happyCallId, salesUser).orElseThrow(HappyCallNotFoundException::new);
        happyCall.read();
        happyCallRepository.save(happyCall);
        log.info("read happy call : {}", happyCallId);
    }

    /**
     * @param pageable
     * @param salesUserInfo
     * # 전화 해야하는 해피콜 목록 가져오기
     */
    @Transactional(readOnly = true)
    public HappyCallModels findTodayHappyCall(Pageable pageable, SalesUserInfo salesUserInfo) {
        return HappyCallModels.builder()
                .happyCallModels(happyCallRepository.findTodayHappyCall(pageable, salesUserInfo))
                .totalElement(happyCallRepository.countTodayCallReservation(salesUserInfo))
                .pageable(pageable)
                .build();
    }
}
