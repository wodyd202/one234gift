package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.domain.read.CallReservationModel;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DynamicUpdate
public class CallReservation extends HappyCall {
    protected CallReservation(){}

    @Builder
    public CallReservation(LocalDate when, CustomerInfo customerInfo, SalesUserInfo salesUser) {
        super(when, customerInfo, salesUser);
    }

    @Override
    public HappyCallModel toModel() {
        return CallReservationModel.builder()
                .seq(seq)
                .salesUser(salesUser)
                .targetCustomer(targetCustomer)
                .when(when)
                .build();
    }
}
