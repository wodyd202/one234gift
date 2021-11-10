package com.one234gift.customerservice.customer.command.application.event;

import com.one234gift.customerservice.customer.domain.value.SaleState;
import lombok.Getter;

/**
 * 고객 상태 변경 이벤트
 */
@Getter
public class ChangedStateEvent extends ChangedCustomerEvent {
    private SaleState state;
    public ChangedStateEvent(Long id, String who,SaleState state) {
        super(id, who);
        this.state = state;
    }
}
