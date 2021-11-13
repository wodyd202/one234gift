package com.one234gift.calllistservice.call.domain.value;

import javax.persistence.Embeddable;

@Embeddable
public class SalesHistoryId {
    private String id;

    protected SalesHistoryId(){}

    public SalesHistoryId(String salesHistoryId) {
        this.id = salesHistoryId;
    }

    public String get() {
        return id;
    }
}
