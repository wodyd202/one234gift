package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubCustomerRepository implements CustomerRepository {
    @Override
    public Customer getCustomer(long customerId) {
        return new Customer(0,null,null, Customer.SaleState.SALE);
    }
}
