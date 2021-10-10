package com.one234gift.customerhistoryservice.application;

import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerHistoryRepository extends JpaRepository<CustomerHistory, Long> {
}
