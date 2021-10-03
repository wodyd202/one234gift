package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.model.ChangeAddressDetail;
import com.one234gift.customerservice.domain.model.ChangeBusinessName;
import com.one234gift.customerservice.domain.model.ChangeBusinessNumber;
import com.one234gift.customerservice.domain.model.ChangeFax;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChangeCustomerService_Test {
    @Autowired
    RegisterCustomerService registerCustomerService;

    @Autowired
    ChangeCustomerService changeCustomerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    CustomerHistoryRepository customerHistoryRepository;

    @BeforeEach
    void setUp(){
        changeCustomerService.setUserRepository(new StubUserRepository());
        registerCustomerService.setUserRepository(new StubUserRepository());
    }

    @Test
    void 업체명_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeCustomerService.changeBusinessName(customer.getId(), ChangeBusinessName.builder()
                .name("업체명 수정")
                .build(), "userId");
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get().toModel();
        assertEquals(findCustomer.getBusinessInfo().getName(),"업체명 수정");
        assertEquals(findCustomer.getBusinessInfo().getNumber(), customer.getBusinessInfo().getNumber());
    }

    @Test
    void 상세주소_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeCustomerService.changeAddressDetail(customer.getId(), ChangeAddressDetail.builder()
                .detail("상세 주소 변경")
                .build(), "userId");
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get().toModel();
        assertEquals(findCustomer.getAddress().getAddressDetail(),"상세 주소 변경");
    }

    @Test
    void 사업자번호_수정(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeCustomerService.changeBusinessNumber(customer.getId(), ChangeBusinessNumber.builder()
                .businessNumber("000-00-12345")
                .build(), "userId");
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get().toModel();
        assertEquals(findCustomer.getBusinessInfo().getNumber(), "000-00-12345");
    }

    @Test
    void 팩스번호_변경(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeCustomerService.changeFax(customer.getId(), ChangeFax.builder().fax("123-1234-1234").build(), "userId");
        CustomerModel findCustomer = customerRepository.findById(customer.getId()).get().toModel();
        assertEquals(findCustomer.getFax(), "123-1234-1234");
    }
}
