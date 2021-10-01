package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.model.ChangeBusinessInfo;
import com.one234gift.customerservice.domain.model.ChangeBusinessName;
import com.one234gift.customerservice.domain.model.ChangeBusinessNumber;
import com.one234gift.customerservice.domain.read.BusinessInfoModel;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.Objects;

public class BusinessInfo {
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "business_name", nullable = false, length = 30))
    private BusinessName name;
    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "business_number", length = 12))
    private BusinessNumber number;

    protected BusinessInfo(){name = null;}

    public BusinessInfo(ChangeBusinessInfo businessInfo) {
        setName(businessInfo.getName());
        setNumber(businessInfo.getNumber());
    }

    public void changeBusinessNumber(ChangeBusinessNumber businessNumber) {
        setNumber(businessNumber.getBusinessNumber());
    }

    public void changeBusinessName(ChangeBusinessName businessName) {
        setName(businessName.getName());
    }

    private void setName(String name) {
        nameValidation(name);
        this.name = new BusinessName(name);
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
