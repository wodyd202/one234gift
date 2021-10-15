package com.one234gift.saleshistoryservice.query.application;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.query.model.SalesHistoryModels;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QuerySalesHistoryService {
    private QuerySaleshistoryRepository saleshistoryRepository;

    public QuerySalesHistoryService(QuerySaleshistoryRepository saleshistoryRepository) {
        this.saleshistoryRepository = saleshistoryRepository;
    }

    public SalesHistoryModels findAll(long customerId, Pageable pageable) {
        return SalesHistoryModels.builder()
                .salesHistoryModels(saleshistoryRepository.findAll(customerId, pageable))
                .totalElement(saleshistoryRepository.countAll(customerId))
                .pageable(pageable)
                .build();
    }
}
