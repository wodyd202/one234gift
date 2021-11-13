package com.one234gift.happycallservice.domain.read;

import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.Reserver;
import lombok.Builder;

import java.time.LocalDate;

public class CallReservationModel extends HappyCallModel {
    @Builder
    public CallReservationModel(Long seq, LocalDate when, Reserver salesUser, CustomerInfo targetCustomer) {
        super(seq,when,salesUser,targetCustomer,"callReservation");
    }
}
