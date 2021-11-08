package com.one234gift.saleshistoryservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pageable {
    private int page;
    private int size;
}
