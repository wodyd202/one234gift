package com.one234gift.saleshistoryservice.salesHistory.domain.value;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerId {
    private long id;

    protected CustomerId() {}

    public CustomerId(long id) {
        this.id = id;
    }

    public long get() {
        return id;
    }
}
