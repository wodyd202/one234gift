package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.infrastructure.QuerydslQueryCustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegisterCustomerService_Test {
    @Autowired RegisterCustomerService registerCustomerService;
    @Autowired QuerydslQueryCustomerRepository customerRepository;

    @Test
    void 고객_등록(){
        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        CustomerModel customerModel = registerCustomerService.register(registerCustomer);

        CustomerModel customer = customerRepository.findById(customerModel.getId()).get();
        assertNotNull(customer);
    }
}
