package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesHistoryRepository extends JpaRepository<SalesHistory, SalesHistoryId> {
}
