package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.CustomerAPITest;
import com.one234gift.customerservice.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.one234gift.customerservice.domain.CustomerFixture.aRegisterCustomer;
import static com.one234gift.customerservice.domain.value.SaleState.SALE;
import static com.one234gift.customerservice.domain.value.SaleState.STOP;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeSaleStateService_Test extends CustomerAPITest {

    @Autowired
    ChangeSaleStateService changeSaleStateService;

    CustomerModel customer;
    @Override
    public void init(){
        userRepository.save("userId");
        customer = registerCustomer(aRegisterCustomer().build());
    }

    @Test
    void 영업_중단(){
        // when
        changeSaleStateService.saleStop(customer.getId());
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getSaleState(), STOP);
    }

    @Test
    void 영업중으로_변경(){
        //given
        changeSaleStateService.saleStop(customer.getId());

        //when
        changeSaleStateService.sale(customer.getId());
        CustomerModel findCustomer = getCustomer(customer.getId());

        // then
        assertEquals(findCustomer.getSaleState(), SALE);
    }
}
