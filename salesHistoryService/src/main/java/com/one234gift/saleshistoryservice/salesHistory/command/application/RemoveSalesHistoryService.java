package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.salesHistory.command.application.SalesHistoryServiceHelper.getSalesHistory;


/**
 * 영업 기록 삭제
 */
@Service
@Transactional
@AllArgsConstructor
public class RemoveSalesHistoryService {
    private SalesHistoryRepository salesHistoryRepository;

    /**
     * @param salesHistoryId
     * @param deleter
     * # 영업 기록 삭제
     */
    public void remove(SalesHistoryId salesHistoryId, Writer deleter) {
        SalesHistory salesHistory = getSalesHistory(salesHistoryRepository, salesHistoryId);
        if(!salesHistory.removeAble(deleter)){
            throw new IllegalArgumentException("다른 영업 사원의 영업 기록을 삭제할 수 없습니다.");
        }
        salesHistoryRepository.deleteById(salesHistoryId);
    }
}
