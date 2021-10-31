package com.one234gift.happycallservice.domain.read;

import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.Builder;

import java.time.LocalDate;

public class CallReservationModel extends HappyCallModel {
    @Builder
    public CallReservationModel(Long seq, LocalDate when, SalesUserInfo salesUser, CustomerInfo targetCustomer, boolean read) {
        super(seq,when,salesUser,targetCustomer,read);
    }
}
