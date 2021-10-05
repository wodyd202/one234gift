package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static com.one234gift.customerservice.domain.value.SaleState.SALE;
import static com.one234gift.customerservice.domain.value.SaleState.STOP;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChangeSaleStateService_Test {
    @Autowired
    RegisterCustomerService registerCustomerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ChangeSaleStateService changeSaleStateService;

    @BeforeEach
    void setUp(){
        StubUserRepository userRepository = new StubUserRepository();
        changeSaleStateService.setUserRepository(userRepository);
        registerCustomerService.setUserRepository(userRepository);
    }

    @Test
    void 영업_중단(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeSaleStateService.saleStop(customer.getId());

        CustomerModel findCustomer = CustomerServiceHelper.findCustomer(customerRepository, customer.getId()).toModel();
        assertEquals(findCustomer.getSaleState(), STOP);
    }

    @Test
    void 영업중으로_변경(){
        CustomerModel customer = registerCustomerService.register(aRegisterCustomer().build(), "userId");
        changeSaleStateService.saleStop(customer.getId());
        changeSaleStateService.sale(customer.getId());

        CustomerModel findCustomer = CustomerServiceHelper.findCustomer(customerRepository, customer.getId()).toModel();
        assertEquals(findCustomer.getSaleState(), SALE);
    }
}
