package com.one234gift.customerservice.query.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerModel {
    private String name, phone;

    @Builder
    public ManagerModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
