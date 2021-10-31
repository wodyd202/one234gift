package com.one234gift.happycallservice.domain;

import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.read.OrderHappyCallModel;
import com.one234gift.happycallservice.domain.value.CustomerInfo;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DynamicUpdate
public class OrderHappyCall extends HappyCall {

    protected OrderHappyCall(){}

    @Builder
    public OrderHappyCall(LocalDate when, CustomerInfo customerInfo, SalesUserInfo salesUser) {
        super(when, customerInfo, salesUser);
    }

    @Override
    public HappyCallModel toModel() {
        return OrderHappyCallModel.builder()
                .seq(seq)
                .salesUser(salesUser)
                .targetCustomer(targetCustomer)
                .when(when)
                .build();
    }
}
