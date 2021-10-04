package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.SalesHistory;

import java.util.Optional;

public interface SalesHistoryRepository {
    void save(SalesHistory salesHistory);
    Optional<SalesHistory> findByIdAndUserId(long id, String userId);
    void remove(Long id);
}
