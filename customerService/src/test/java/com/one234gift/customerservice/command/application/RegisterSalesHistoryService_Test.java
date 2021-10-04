package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.model.RegisterSalesHistory;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.one234gift.customerservice.domain.CustomerFixture.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterSalesHistoryService_Test {
    @Autowired RegisterSalesHistoryService registerSalesHistoryService;
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        registerSalesHistoryService.setCustomerRepository(customerRepository);

        StubUserRepository userRepository = new StubUserRepository();
        registerSalesHistoryService.setUserRepository(userRepository);

        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        Customer customer = Customer.registerWith(registerCustomer, aManager().build());
        customer.register(mock(RegisterCustomerValidator.class));

        when(customerRepository.findById(any()))
                .thenReturn(Optional.of(customer));
    }

    @Test
    void 판매중단된_고객(){
        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        Customer customer = Customer.registerWith(registerCustomer, aManager().build());
        customer.register(mock(RegisterCustomerValidator.class));
        customer.saleStop();

        when(customerRepository.findById(2L))
                .thenReturn(Optional.of(customer));

        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        Long customerId = 2L;
        assertThrows(CustomerNotFoundException.class,()->{
            registerSalesHistoryService.register(customerId,registerSalesHistory);
        });
    }

    @Test
    void 영업기록_생성(){
        RegisterSalesHistory registerSalesHistory = aRegisterSalesHistory().build();
        Long customerId = 1L;
        SalesHistoryModel salesHistoryModel = registerSalesHistoryService.register(customerId,registerSalesHistory);
        assertNotNull(salesHistoryModel);
    }
}
