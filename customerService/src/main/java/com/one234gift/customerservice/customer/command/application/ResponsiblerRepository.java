package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.domain.value.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponsiblerRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByCustomerIdAndManager(Long customerId, String manager);
}