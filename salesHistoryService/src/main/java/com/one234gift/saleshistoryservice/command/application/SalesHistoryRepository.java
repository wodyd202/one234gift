package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.domain.SalesHistory;

import java.util.Optional;

public interface SalesHistoryRepository {
    void save(SalesHistory salesHistory);
    Optional<SalesHistory> findByIdAndUserId(long id, String userId);
    boolean existByIdAndUserId(Long id, String userId);
    void remove(Long id);
}
