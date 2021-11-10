package com.one234gift.customerservice.customer.domain.value;

import com.one234gift.customerservice.customer.command.application.model.ChangeBusinessInfo;
import com.one234gift.customerservice.customer.domain.read.BusinessInfoModel;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

/**
 * 고객 정보
 * # 고객 업체명
 * # 사업자 번호
 */
@Embeddable
public class BusinessInfo {

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "business_name", nullable = false, length = 30))
    private BusinessName name;
    @Embedded
    @AttributeOverride(name = "number", column = @Column(name = "business_number", length = 12))
    private BusinessNumber number;

    protected BusinessInfo(){name = null;}

    public BusinessInfo(ChangeBusinessInfo businessInfo) {
        setName(new BusinessName(businessInfo.getName()));
        setNumber(new BusinessNumber(businessInfo.getNumber()));
    }

    /**
     * @param businessNumber
     * # 사업자 번호 변경
     */
    public void changeBusinessNumber(BusinessNumber businessNumber) {
        setNumber(businessNumber);
    }

    /**
     * @param businessName
     * # 고객 업체명 변경
     */
    public void changeBusinessName(BusinessName businessName) {
        setName(businessName);
    }

    private void setName(BusinessName name) {
        nameValidation(name);
        this.name = name;
    }

    private void setNumber(BusinessNumber number) {
        if(number == null){
            this.number = BusinessNumber.getInstance();
            return;
        }
        this.number = number;
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
                .number(getNumber().get())
                .build();
    }

    private BusinessNumber getNumber() {
        return number == null ? new BusinessNumber() : number;
    }
}
