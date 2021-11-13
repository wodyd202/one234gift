package com.one234gift.customerservice.customer;

import com.one234gift.customerservice.APITest;
import com.one234gift.customerservice.customer.command.application.RegisterCustomerService;
import com.one234gift.customerservice.customer.command.application.StubUserRepository;
import com.one234gift.customerservice.customer.command.application.model.RegisterCustomer;
import com.one234gift.customerservice.customer.domain.exception.CustomerNotFoundException;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.query.infrastructure.QuerydslQueryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerAPITest extends APITest {
    @Autowired
    public RegisterCustomerService registerCustomerService;
    @Autowired
    public QuerydslQueryCustomerRepository customerRepository;

    public void init(){}

    @BeforeEach
    void setUp() {
        init();
    }

    protected CustomerModel registerCustomer(RegisterCustomer registerCustomer) {
        return registerCustomerService.register(registerCustomer);
    }

    protected CustomerModel getCustomer(long customerId){
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
