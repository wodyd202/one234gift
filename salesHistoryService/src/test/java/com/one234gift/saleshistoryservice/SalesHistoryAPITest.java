package com.one234gift.saleshistoryservice;

import com.one234gift.saleshistoryservice.command.StubUserRepository;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.query.application.QuerySaleshistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SalesHistoryAPITest extends APITest {
    @Autowired StubUserRepository stubUserRepository;
    @Autowired QuerySaleshistoryRepository saleshistoryRepository;

    protected Optional<SalesHistoryModel> findSalesHistory(long salesHistoryId){
        return saleshistoryRepository.findBySalesHistoryId(salesHistoryId);
    }

    protected void newUser(String userId){
        stubUserRepository.save(userId);
    }

    protected SalesHistoryModel newSalesHistory(RegisterSalesHistory registerSalesHistory){
        return registerSalesHistoryService.register(registerSalesHistory);
    }
}
