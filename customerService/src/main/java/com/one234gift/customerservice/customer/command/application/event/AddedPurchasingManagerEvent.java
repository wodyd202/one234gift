package com.one234gift.customerservice.customer.command.application.event;

import com.one234gift.customerservice.customer.domain.read.PurchasingManagerModel;
import lombok.Getter;

/**
 * 구매 담당자 추가 이벤트
 */
@Getter
public class AddedPurchasingManagerEvent extends ChangedCustomerEvent {
    private PurchasingManagerModel purchasingManager;
    public AddedPurchasingManagerEvent(Long id, String who, PurchasingManagerModel purchasingManager) {
        super(id, who);
        this.purchasingManager = purchasingManager;
    }
}
