package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class ResponsibleService_Test {
    @Autowired ResponsibleService responsibleService;
    @Autowired RegisterCustomerService registerCustomerService;

    @Test
    void 고객담당_등록(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());

        assertDoesNotThrow(()->{
            responsibleService.flag(customer.getId(), "manager");
        });
    }

    @Test
    void 고객담당_등록후_해제(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build());

        assertDoesNotThrow(()->{
            responsibleService.flag(customer.getId(), "manager1");
            responsibleService.flag(customer.getId(), "manager1");
        });
    }

}
