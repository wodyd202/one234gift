package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.model.*;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import static com.one234gift.customerservice.domain.CustomerFixture.aAddPurchasingManager;
import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChangeCustomerService_Test {
    @Autowired
    RegisterCustomerService registerCustomerService;

    @Autowired
    ChangeCustomerService changeCustomerService;

    @Autowired
    QueryCustomerRepository customerRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void setUp(){
        changeCustomerService.setUserRepository(new StubUserRepository());
    }

    @Test
    void 업체명_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());
        changeCustomerService.changeBusinessName(customer.getId(), ChangeBusinessName.builder()
                .name("업체명 수정")
                .build());
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getBusinessInfo().getName(),"업체명 수정");
        assertEquals(findCustomer.getBusinessInfo().getNumber(), customer.getBusinessInfo().getNumber());
    }

    @Test
    void 상세주소_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());
        changeCustomerService.changeAddressDetail(customer.getId(), ChangeAddressDetail.builder()
                .detail("상세 주소 변경")
                .build());
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getAddress().getAddressDetail(),"상세 주소 변경");
    }

    @Test
    void 사업자번호_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());
        changeCustomerService.changeBusinessNumber(customer.getId(), ChangeBusinessNumber.builder()
                .businessNumber("000-00-12345")
                .build());
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getBusinessInfo().getNumber(), "000-00-12345");
    }

    @Test
    void 팩스번호_변경(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());
        changeCustomerService.changeFax(customer.getId(), ChangeFax.builder().fax("123-1234-1234").build());
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getFax(), "123-1234-1234");
    }

    @Test
    void 담당자_추가(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());
        changeCustomerService.addPurchasingManager(customer.getId(), aAddPurchasingManager().build());
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getPurchasingManagers().size(), 2);
    }

    @Test
    void 담당자_삭제(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().purchasingManagers(asList(aAddPurchasingManager()
                .name("이름")
                .contact(ChangeContact.builder()
                        .mainTel("000-0000-9999")
                        .build())
                .build())).build());
        changeCustomerService.removePurchasingManager(customer.getId(), RemovePurchasingManager.builder().mainTel("000-0000-9999").name("이름").build());

        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get();
        assertEquals(findCustomer.getPurchasingManagers().size(), 0);
    }

}
