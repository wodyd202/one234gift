package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.RegisterCustomerValidator;
import com.one234gift.customerservice.domain.model.ChangeCallReservationDate;
import com.one234gift.customerservice.domain.model.ChangeCustomerReactivity;
import com.one234gift.customerservice.domain.model.ChangeSalesHistoryContent;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import com.one234gift.customerservice.domain.value.CustomerReactivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static com.one234gift.customerservice.domain.CustomerFixture.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChangeSalesHistoryService_Test {
    @Autowired ChangeSalesHistoryService changeSalesHistoryService;
    @Autowired
    RegisterSalesHistoryService registerSalesHistoryService;
    SalesHistoryModel register;

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
        register = registerSalesHistoryService.register(1L, aRegisterSalesHistory().build());
    }

    @Test
    void 샘플_유무_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeSample(register.getId(), "010-0000-0000");
        assertTrue(salesHistoryModel.isSample());
    }

    @Test
    void 카탈로그_유무_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCatalogue(register.getId(), "010-0000-0000");
        assertTrue(salesHistoryModel.isCatalogue());
    }

    @Test
    void 내용_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeContent(register.getId(), ChangeSalesHistoryContent.builder()
                        .content("내용 변경")
                .build(),"010-0000-0000");
        assertEquals(salesHistoryModel.getContent(), "내용 변경");
    }

    @Test
    void 예약콜_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeCallReservationDate(register.getId(), ChangeCallReservationDate.builder()
                        .callReservationDate(LocalDate.now().plusDays(2))
                .build(),"010-0000-0000");
        assertEquals(salesHistoryModel.getCallReservationDate(), LocalDate.now().plusDays(2));
    }

    @Test
    void 반응도_변경(){
        SalesHistoryModel salesHistoryModel = changeSalesHistoryService.changeReactivity(register.getId(), ChangeCustomerReactivity.builder()
                        .reactivity(CustomerReactivity.FIVE)
                .build(),"010-0000-0000");
        assertEquals(salesHistoryModel.getReactivity(), CustomerReactivity.FIVE);
    }
}
