package com.one234gift.customerservice.domain.read;

import com.one234gift.customerservice.domain.value.BusinessName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfoModel {
    private String name;
    private String number;

    @Builder
    public BusinessInfoModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "BusinessInfoModel{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
