package com.one234gift.calllistservice.call.domain.value;

import javax.persistence.Embeddable;

/**
 * 예약자
 */
@Embeddable
public class Reserver {
    private String id;

    protected Reserver(){}

    public Reserver(String reserver) {
        this.id = reserver;
    }

    public String get() {
        return id;
    }
}
