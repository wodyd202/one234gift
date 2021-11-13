package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerReactivity;
import lombok.Getter;

@Getter
public class ChangedReactivityEvent extends AbstractSalesHistoryEvent{
    private CustomerReactivity reactivity;

    public ChangedReactivityEvent(String salesHistoryId, CustomerReactivity reactivity) {
        super(salesHistoryId);
        this.reactivity = reactivity;
    }
}
