package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.event.CallReserved;
import com.one234gift.saleshistoryservice.command.application.external.Customer;
import com.one234gift.saleshistoryservice.command.application.external.CustomerRepository;
import com.one234gift.saleshistoryservice.command.application.external.Employee;
import com.one234gift.saleshistoryservice.command.application.external.EmployeeRepository;
import com.one234gift.saleshistoryservice.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.command.application.util.ProcessUserIdGetter;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.command.application.SalesHistoryServiceHelper.getEmployee;

/**
 * 영업 기록 생성 서비스
 */
@Service
@Setter
@Transactional
@AllArgsConstructor
public class RegisterSalesHistoryService {
    private SalesHistoryMapper salesHistoryMapper;
    private SalesHistoryRepository salesHistoryRepository;
    private ProcessUserIdGetter userIdGetter;
    private ApplicationEventPublisher applicationEventPublisher;

    // 외부 모듈
    private EmployeeRepository userRepository;
    private CustomerRepository customerRepository;

    /**
     * 영업 기록 생성
     */
    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory) {
        // 고객 존재 여부 확인
        verifySaleCustomer(registerSalesHistory);

        // 작성자 정보 조회
        Employee employee = getEmployee(userRepository, userIdGetter);

        // map
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, employee);
        salesHistoryRepository.save(salesHistory);

        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        if(salesHistoryModel.existCallReservation()){
            applicationEventPublisher.publishEvent(new CallReserved(salesHistoryModel));
        }
        return salesHistoryModel;
    }

    private void verifySaleCustomer(RegisterSalesHistory registerSalesHistory) {
        Customer customer = customerRepository.getCustomer(registerSalesHistory.getCustomerId());
        if(!customer.isSale()){
            throw new IllegalArgumentException("현재 영업중인 고객만 영업 기록을 등록할 수 있습니다.");
        }
    }
}
