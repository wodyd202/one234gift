package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.*;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class CustomerFixture {
    public static Customer aCustomer(){
        Customer customer = Customer.registerWith(aRegisterCustomer().build());
        customer.register(mock(RegisterCustomerValidator.class));
        return customer;
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

    public static ChangePurchasingManager.ChangePurchasingManagerBuilder aAddPurchasingManager(){
        return ChangePurchasingManager.builder()
                .name("이름")
                .email("test@google.com")
                .jobTitle("직위")
                .contact(ChangeContact.builder()
                        .mainTel("000-0000-0000")
                        .build());
    }
}
