package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerservice.domain.CustomerFixture.aManager;
import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegisterCustomerService_Test {
    @Autowired RegisterCustomerService registerCustomerService;
    @Autowired CustomerRepository customerRepository;

    @Test
    void 고객_등록(){
        CustomerModel customerModel = registerCustomerService.register(aRegisterCustomer().build(), aManager().build());
        Customer customer = customerRepository.findById(customerModel.getId()).get();
        assertNotNull(customer);
    }
}
