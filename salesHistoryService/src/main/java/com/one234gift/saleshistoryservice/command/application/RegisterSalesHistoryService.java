package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.event.CallReserved;
import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 영업 기록 생성 서비스
 */
@Service
@Setter
@Transactional
@AllArgsConstructor
public class RegisterSalesHistoryService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private SalesHistoryRepository salesHistoryRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory) {
        verifyExistCustomer(registerSalesHistory);
        Writer writer = SalesHistoryServiceHelper.findUser(userRepository);

        SalesHistory salesHistory = SalesHistory.register(registerSalesHistory, writer);
        salesHistoryRepository.save(salesHistory);

        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        if(salesHistoryModel.existCallReservation()){
            applicationEventPublisher.publishEvent(new CallReserved(salesHistoryModel));
        }
        return salesHistoryModel;
    }

    private void verifyExistCustomer(RegisterSalesHistory registerSalesHistory) {
        if(!customerRepository.existByCustomer(registerSalesHistory.getCustomerId())){
            throw new CustomerNotFoundException();
        }
    }
}
