package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponsiblerRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByCustomerIdAndManager(Long customerId, String manager);
}