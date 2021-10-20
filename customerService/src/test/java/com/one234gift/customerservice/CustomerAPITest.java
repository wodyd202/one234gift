package com.one234gift.customerservice;

import com.one234gift.customerservice.command.application.RegisterCustomerService;
import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.command.infrastructure.StubUserRepository;
import com.one234gift.customerservice.domain.model.RegisterCustomer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.infrastructure.QuerydslQueryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerAPITest extends APITest {
    @Autowired
    public RegisterCustomerService registerCustomerService;
    @Autowired
    public QuerydslQueryCustomerRepository customerRepository;
    @Autowired
    public StubUserRepository userRepository;

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
