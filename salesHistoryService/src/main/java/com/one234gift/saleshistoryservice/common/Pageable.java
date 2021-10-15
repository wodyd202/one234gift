package com.one234gift.saleshistoryservice.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {
    private int page;
    private int size;

    @Override
    public String toString() {
        return "Pageable{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
