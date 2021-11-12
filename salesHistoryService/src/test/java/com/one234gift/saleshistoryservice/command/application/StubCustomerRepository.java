package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.external.Customer;
import com.one234gift.saleshistoryservice.command.application.external.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubCustomerRepository implements CustomerRepository {
    @Override
    public Customer getCustomer(long customerId) {
        return new Customer();
    }
}
