package com.one234gift.saleshistoryservice.salesHistory.domain.read;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WriterModel {
    private String name, phone;

    @Builder
    public WriterModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
