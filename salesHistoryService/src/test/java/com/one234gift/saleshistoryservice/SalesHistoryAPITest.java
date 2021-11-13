package com.one234gift.saleshistoryservice;

import com.one234gift.saleshistoryservice.salesHistory.command.application.StubUserRepository;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.SalesHistoryId;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
import com.one234gift.saleshistoryservice.salesHistory.query.application.QuerySaleshistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SalesHistoryAPITest extends APITest {
    @Autowired StubUserRepository stubUserRepository;
    @Autowired QuerySaleshistoryRepository saleshistoryRepository;

    protected Optional<SalesHistoryModel> findSalesHistory(SalesHistoryId salesHistoryId){
        return saleshistoryRepository.findBySalesHistoryId(salesHistoryId);
    }

    protected SalesHistoryModel newSalesHistory(RegisterSalesHistory registerSalesHistory, Writer writer){
        return registerSalesHistoryService.register(registerSalesHistory, writer);
    }

    protected SalesHistoryId getSalesHistoryId(String id) {
        return new SalesHistoryId(id);
    }

    protected Writer getWriter(String writer){
        return Writer.builder().phone(writer).name(writer).build();
    }
}
