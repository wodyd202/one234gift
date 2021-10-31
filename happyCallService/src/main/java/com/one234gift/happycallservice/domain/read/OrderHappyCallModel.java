package com.one234gift.happycallservice.domain.read;

import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.Builder;

import java.time.LocalDate;

public class OrderHappyCallModel extends HappyCallModel {

    @Builder
    public OrderHappyCallModel(Long seq, LocalDate when, SalesUserInfo salesUser, CustomerInfo targetCustomer, boolean read) {
        super(seq,when,salesUser,targetCustomer, read);
    }

}
