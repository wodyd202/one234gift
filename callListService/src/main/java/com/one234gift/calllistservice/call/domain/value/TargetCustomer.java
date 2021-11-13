package com.one234gift.calllistservice.call.domain.value;

import com.one234gift.calllistservice.call.domain.read.TargetCustomerModel;
import lombok.Builder;

import javax.persistence.Embeddable;

/**
 * 고객 정보
 */
@Embeddable
public class TargetCustomer {
    private long customerId;
    private String category;
    private String name;

    protected TargetCustomer(){}

    @Builder
    public TargetCustomer(long customerId, String category, String name) {
        this.customerId = customerId;
        this.category = category;
        this.name = name;
    }

    public TargetCustomerModel toModel(){
        return TargetCustomerModel.builder()
                .category(category)
                .customerId(customerId)
                .name(name)
                .build();
    }
}
