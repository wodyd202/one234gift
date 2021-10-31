package com.one234gift.happycallservice.application;

import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.model.SalesUser;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;

import java.util.List;
import java.util.Optional;

public interface HappyCallRepository {
    void save(HappyCall happyCall);
    Optional<HappyCall> findByIdAndSalesUser(long seq, SalesUserInfo salesUser);
    List<HappyCallModel> findTodayHappyCallsByUserId(SalesUserInfo salesUser);
}
