package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.CustomerAPITest;
import com.one234gift.customerservice.customer.command.application.model.RegisterCustomer;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import org.junit.jupiter.api.Test;

import static com.one234gift.customerservice.customer.CustomerFixture.aRegisterCustomer;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 고객 등록 서비스 테스트
 */
public class RegisterCustomerService_Test extends CustomerAPITest {

    @Test
    void 고객_등록(){
        // given
        RegisterCustomer registerCustomer = aRegisterCustomer().build();
        CustomerModel customerModel = registerCustomerService.register(registerCustomer);

        // when
        CustomerModel customer = customerRepository.findById(customerModel.getId()).get();

        // then
        assertNotNull(customer);
    }
}
