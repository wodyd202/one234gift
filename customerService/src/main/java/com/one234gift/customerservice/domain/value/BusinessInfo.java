package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.BusinessInfoModel;

import java.util.Objects;

public class BusinessInfo {
    private final BusinessName name;
    private BusinessNumber number;

    public BusinessInfo(ChangeBusinessInfo businessInfo) {
        nameValidation(businessInfo.getName());
        name = new BusinessName(businessInfo.getName());
        setNumber(businessInfo.getNumber());
    }


    private void setNumber(String number) {
        if(number == null){
            this.number = new BusinessNumber();
        }else{
            this.number = new BusinessNumber(number);
        }
    }

    private void nameValidation(String name) {
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
