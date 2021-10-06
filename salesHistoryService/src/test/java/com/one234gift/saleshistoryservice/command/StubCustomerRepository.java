package com.one234gift.saleshistoryservice.command;

import com.one234gift.saleshistoryservice.command.application.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class StubCustomerRepository implements CustomerRepository {
    @Override
    public boolean existByCustomer(long customerId) {
        return true;
    }
}
