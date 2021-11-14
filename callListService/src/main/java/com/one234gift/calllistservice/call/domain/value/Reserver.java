package com.one234gift.calllistservice.call.domain.value;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserver reserver = (Reserver) o;
        return Objects.equals(id, reserver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
