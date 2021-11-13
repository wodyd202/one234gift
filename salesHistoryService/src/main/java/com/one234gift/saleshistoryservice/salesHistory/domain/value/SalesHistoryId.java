package com.one234gift.saleshistoryservice.salesHistory.domain.value;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SalesHistoryId implements Serializable {
    private String id;

    protected SalesHistoryId(){}

    public SalesHistoryId(String id) {
        this.id = id;
    }

    public String get() {
        return id;
    }
}
