package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.CustomerAPITest;
import com.one234gift.customerservice.customer.domain.exception.PurchasingManagerNotFoundException;
import com.one234gift.customerservice.customer.command.application.model.*;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.one234gift.customerservice.customer.CustomerFixture.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeCustomerService_Test extends CustomerAPITest {
    @Autowired
    ChangeCustomerService changeCustomerService;

    CustomerModel customer;
    @Override
    public void init(){
        customer = registerCustomer(aRegisterCustomer().build());
    }

    @Test
    void 업체명_수정(){
        // given
        ChangeBusinessName changeBusinessName = ChangeBusinessName.builder()
                .name("업체명 수정")
                .build();

        // when
        changeCustomerService.changeBusinessName(customer.getId(), changeBusinessName, aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getBusinessInfo().getName(),"업체명 수정");
        assertEquals(findCustomer.getBusinessInfo().getNumber(), customer.getBusinessInfo().getNumber());
    }

    @Test
    void 상세주소_수정(){
        // given
        ChangeAddressDetail changeAddressDetail = ChangeAddressDetail.builder()
                .detail("상세 주소 변경")
                .build();

        // when
        changeCustomerService.changeAddressDetail(customer.getId(), changeAddressDetail, aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getAddress().getAddressDetail(),"상세 주소 변경");
    }

    @Test
    void 사업자번호_수정(){
        // given
        ChangeBusinessNumber changeBusinessNumber = ChangeBusinessNumber.builder()
                .businessNumber("000-00-12345")
                .build();

        // when
        changeCustomerService.changeBusinessNumber(customer.getId(), changeBusinessNumber, aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getBusinessInfo().getNumber(), "000-00-12345");
    }

    @Test
    void 팩스번호_변경(){
        // given
        ChangeFax changeFax = ChangeFax.builder().fax("123-1234-1234").build();

        // when
        changeCustomerService.changeFax(customer.getId(), changeFax, aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getFax(), "123-1234-1234");
    }

    @Test
    void 담당자_추가(){
        // given
        ChangePurchasingManager changePurchasingManager = aAddPurchasingManager().build();

        // when
        changeCustomerService.addPurchasingManager(customer.getId(), changePurchasingManager, aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getPurchasingManagers().size(), 2);
    }

    @Test
    void 담당자_삭제(){
        // given
        List<ChangePurchasingManager> purchasingManagers = asList(aAddPurchasingManager()
                .name("이름")
                .contact(ChangeContact.builder()
                        .mainTel("000-0000-9999")
                        .build())
                .build());
        customer = registerCustomerService.register(aRegisterCustomer().purchasingManagers(purchasingManagers).build());

        // when
        changeCustomerService.removePurchasingManager(customer.getId(), RemovePurchasingManager.builder().id(customer.getPurchasingManagers().get(0).getId()).build(), aChanger("변경자"));
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getPurchasingManagers().size(), 0);
    }

    @Test
    void 해당_담당자가_존재하지_않음(){
        assertThrows(PurchasingManagerNotFoundException.class, ()->{
            changeCustomerService.removePurchasingManager(customer.getId(), RemovePurchasingManager.builder().id(1000L).build(), aChanger("변경자"));
        });
    }

}
