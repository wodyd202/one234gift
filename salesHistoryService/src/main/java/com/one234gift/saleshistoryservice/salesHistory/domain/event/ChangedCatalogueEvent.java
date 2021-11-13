package com.one234gift.saleshistoryservice.salesHistory.domain.event;

import lombok.Getter;

@Getter
public class ChangedCatalogueEvent extends AbstractSalesHistoryEvent{
    private boolean catalogue;

    public ChangedCatalogueEvent(String salesHistoryId, boolean catalogue) {
        super(salesHistoryId);
        this.catalogue = catalogue;
    }
}
