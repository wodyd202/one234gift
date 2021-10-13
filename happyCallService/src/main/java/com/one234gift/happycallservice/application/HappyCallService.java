package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.application.exception.HappyCallNotFoundException;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HappyCallService {
    private HappyCallRepository happyCallRepository;

    public HappyCallService(HappyCallRepository happyCallRepository) {
        this.happyCallRepository = happyCallRepository;
    }

    @Transactional(readOnly = true)
    public List<HappyCallModel> findTodayHappyCallsByUserId(String userId) {
        return happyCallRepository.findTodayHappyCallsByUserId(userId);
    }

    @Transactional
    public void read(long happyCallId, String userId){
        HappyCall happyCall = happyCallRepository.findByIdAndUserId(happyCallId, userId).orElseThrow(HappyCallNotFoundException::new);
        happyCall.read();
        happyCallRepository.save(happyCall);
    }

}
