package com.one234gift.customerservice.customer.command.application.event;

import lombok.Getter;

/**
 * 상세 주소 변경 이벤트
 */
@Getter
public class ChangedAddressDetailEvent extends ChangedCustomerEvent {
    private String addressDetail;

    public ChangedAddressDetailEvent(Long id,String who, String addressDetail) {
        super(id, who);
        this.addressDetail = addressDetail;
    }
}
