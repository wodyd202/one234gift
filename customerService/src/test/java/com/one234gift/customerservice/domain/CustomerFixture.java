package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.ChangeAddress;
import com.one234gift.customerservice.domain.model.ChangeBusinessInfo;
import com.one234gift.customerservice.domain.model.ChangeContact;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.value.Manager;
import com.one234gift.customerservice.domain.value.RegisterCustomer;

import java.util.Arrays;

public class CustomerFixture {
    public static Manager.ManagerBuilder aManager(){
        return Manager.builder()
                .name("고객 담당자")
                .phone("휴대폰번호");
    }

    public static RegisterCustomer.RegisterCustomerBuilder aRegisterCustomer() {
        return RegisterCustomer.builder()
                .category("은행")
                .businessInfo(ChangeBusinessInfo.builder()
                        .name("사업체명")
                        .number("000-00-00000")
                        .build())
                .purchasingManagers(Arrays.asList(ChangePurchasingManager.builder()
                        .name("이름")
                        .email("test@google.com")
                        .jobTitle("직위")
                                .contact(ChangeContact.builder()
                                        .mainTel("000-0000-0000")
                                        .build())
                        .build()))
                .fax("00-000-0000")
                .address(ChangeAddress.builder()
                        .location("지역")
                        .addressDetail("상세주소")
                        .build());
    }

}
