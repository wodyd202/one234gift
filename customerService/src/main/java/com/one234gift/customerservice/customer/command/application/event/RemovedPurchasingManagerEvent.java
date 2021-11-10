package com.one234gift.customerservice.customer.command.application.event;

import lombok.Getter;

/**
 * 구매 담당자 삭제 이벤트
 */
@Getter
public class RemovedPurchasingManagerEvent extends ChangedCustomerEvent {
    private long removePurchasingManagerId;

    public RemovedPurchasingManagerEvent(Long id,String who, long removePurchasingManagerId) {
        super(id, who);
        this.removePurchasingManagerId = removePurchasingManagerId;
    }
}
