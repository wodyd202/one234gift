package com.one234gift.saleshistoryservice.command.application;

public interface CustomerRepository {
    boolean existByCustomer(long customerId);
}
