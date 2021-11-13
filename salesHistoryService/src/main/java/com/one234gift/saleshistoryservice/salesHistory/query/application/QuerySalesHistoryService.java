package com.one234gift.saleshistoryservice.salesHistory.query.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.exception.SalesHistoryNotFoundException;
import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.CustomerId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.query.model.SalesHistoryModels;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 영업 기록 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class QuerySalesHistoryService {
    private QuerySaleshistoryRepository saleshistoryRepository;

    /**
     * @param customerId
     * @param pageable
     * # 영업 기록 리스트 조회
     */
    public SalesHistoryModels getSalesHistoryModelsByCustomerId(CustomerId customerId, Pageable pageable) {
        return SalesHistoryModels.builder()
                .salesHistoryModels(saleshistoryRepository.findAll(customerId, pageable))
                .totalElement(saleshistoryRepository.countAll(customerId))
                .pageable(pageable)
                .build();
    }

    /**
     * @param salesHistoryId
     * # 영업 기록 단건 조회
     */
    public SalesHistoryModel getSalesHistoryModel(SalesHistoryId salesHistoryId) {
        return saleshistoryRepository.findBySalesHistoryId(salesHistoryId).orElseThrow(SalesHistoryNotFoundException::new);
    }
}
