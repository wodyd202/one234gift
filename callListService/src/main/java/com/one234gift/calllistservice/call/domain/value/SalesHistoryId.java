package com.one234gift.calllistservice.call.domain.value;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SalesHistoryId implements Serializable {
    private String id;

    protected SalesHistoryId(){}

    public SalesHistoryId(String salesHistoryId) {
        this.id = salesHistoryId;
    }

    public String get() {
        return id;
    }
}
