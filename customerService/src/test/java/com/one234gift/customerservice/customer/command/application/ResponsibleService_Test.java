package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.CustomerAPITest;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.customerservice.customer.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ResponsibleService_Test extends CustomerAPITest {
    @Autowired ResponsibleService responsibleService;

    CustomerModel customer;
    @Override
    public void init() {
        customer = registerCustomer(aRegisterCustomer().build());
    }

    @Test
    void 고객담당_등록(){
        // when
        assertDoesNotThrow(()->{
            responsibleService.flag(customer.getId(), "manager");
        });
    }

    @Test
    void 고객담당_등록후_해제(){
        // given
        responsibleService.flag(customer.getId(), "manager1");

        // when
        assertDoesNotThrow(()->{
            responsibleService.flag(customer.getId(), "manager1");
        });
    }

}
