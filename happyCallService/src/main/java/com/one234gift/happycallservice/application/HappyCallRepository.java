package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.read.HappyCallModel;

import java.util.List;
import java.util.Optional;

public interface HappyCallRepository {
    void save(HappyCall happyCall);
    Optional<HappyCall> findByIdAndUserId(long seq, String userId);
    List<HappyCallModel> findTodayHappyCallsByUserId(String userId);
}
