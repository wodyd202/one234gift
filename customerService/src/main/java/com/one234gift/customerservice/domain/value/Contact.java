package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.model.ChangeContact;
import com.one234gift.customerservice.domain.read.ContectModel;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class Contact {
    @Embedded
    @AttributeOverride(name = "tel", column = @Column(name = "main_tel", nullable = false, length = 13))
    private Tel mainTel;

    @Embedded
    @AttributeOverride(name = "tel", column = @Column(name = "sub_tel", length = 13))
    private Tel subTel;

    protected Contact(){}

    public Contact(ChangeContact contact){
        setMainTel(contact.getMainTel());
        setSubTel(contact.getSubTel());
    }

    private void setMainTel(String mainTel) {
        mainTelValidation(mainTel);
        this.mainTel = new Tel(mainTel);
    }

    private void mainTelValidation(String mainTel) {
        if(mainTel == null){
            throw new IllegalArgumentException("대표 연락처를 입력해주세요.");
        }
    }

    private void setSubTel(String subTel) {
        if(subTel == null){
            this.subTel = new Tel();
        }else{
            this.subTel = new Tel(subTel);
        }
    }

    public ContectModel toModel() {
        return ContectModel.builder()
                .mainTel(mainTel.get())
                .subTel(subTel.get())
                .build();
    }
}
