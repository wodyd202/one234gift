package com.one234gift.customerservice.customer.command.application.event;

import lombok.Getter;

/**
 * 고객 업체명 변경 이벤트
 */
@Getter
public class ChangedBusinessNameEvent extends ChangedCustomerEvent{
    private String businessName;

    public ChangedBusinessNameEvent(Long id,String who, String businessName) {
        super(id, who);
        this.businessName = businessName;
    }
}
