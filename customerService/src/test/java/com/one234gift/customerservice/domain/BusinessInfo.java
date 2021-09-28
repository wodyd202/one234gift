package com.one234gift.customerservice.domain;

import lombok.Builder;

import java.util.Objects;

public class BusinessInfo {
    private final BusinessName name;
    private BusinessNumber number;

    @Builder
    public BusinessInfo(BusinessName name, BusinessNumber number) {
        nameValidation(name);
        this.name = name;
        if(number == null){
            this.number = new BusinessNumber();
        }else{
            this.number = number;
        }
    }

    private void nameValidation(BusinessName name) {
        if(name == null){
            throw new IllegalArgumentException("고객 업체명을 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessInfo that = (BusinessInfo) o;
        return Objects.equals(name, that.name) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    public BusinessInfoModel toModel() {
        return BusinessInfoModel.builder()
                .name(name.get())
                .number(number.get())
                .build();
    }
}
