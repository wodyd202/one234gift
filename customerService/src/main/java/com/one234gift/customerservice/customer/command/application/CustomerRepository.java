package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
