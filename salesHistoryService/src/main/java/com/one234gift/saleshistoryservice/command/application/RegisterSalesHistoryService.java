package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.event.CallReserved;
import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.command.application.external.CustomerRepository;
import com.one234gift.saleshistoryservice.command.application.external.UserRepository;
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
    private SalesHistoryRepository salesHistoryRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    // 외부 모듈
    private UserRepository userRepository;
    private CustomerRepository customerRepository;

    /**
     * 영업 기록 생성
     */
    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory) {
        // 고객 존재 여부 확인
        verifyExistCustomer(registerSalesHistory);

        // 작성자 조회
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
