package com.one234gift.customerservice.customer.command.application.event;

import lombok.Getter;

/**
 * 사업자 번호 변경 이벤트
 */
@Getter
public class ChangedBusinessNumberEvent extends ChangedCustomerEvent {
    private String businessNumber;
    public ChangedBusinessNumberEvent(Long id,String who, String businessNumber) {
        super(id, who);
        this.businessNumber = businessNumber;
    }
}
