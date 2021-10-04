package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.one234gift.customerservice.domain.CustomerFixture.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RemoveSalesHistoryService_Test {
    @Autowired RemoveSalesHistoryService removeSalesHistoryService;
    @Autowired RegisterSalesHistoryService registerSalesHistoryService;
    SalesHistoryModel salesHistoryModel;
    @Autowired SalesHistoryRepository salesHistoryRepository;

    @BeforeEach
    void setUp() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        registerSalesHistoryService.setCustomerRepository(customerRepository);
        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        Customer customer = Customer.registerWith(registerCustomer, aManager().build());
        customer.register(mock(RegisterCustomerValidator.class));

        when(customerRepository.findById(any()))
                .thenReturn(Optional.of(customer));

        registerSalesHistoryService.setUserRepository(new StubUserRepository());
        salesHistoryModel = registerSalesHistoryService.register(1L, aRegisterSalesHistory().build());
    }

    @Test
    void 영업기록_삭제(){
        removeSalesHistoryService.remove(salesHistoryModel.getId(), "010-0000-0000");

        assertFalse(salesHistoryRepository.findByIdAndUserId(salesHistoryModel.getId(), "010-0000-0000").isPresent());
    }

}
