package com.one234gift.saleshistoryservice.salesHistory.command.application;

import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Customer;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.CustomerRepository;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.Employee;
import com.one234gift.saleshistoryservice.salesHistory.command.application.external.EmployeeRepository;
import com.one234gift.saleshistoryservice.salesHistory.command.application.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.SalesHistory;
import com.one234gift.saleshistoryservice.salesHistory.domain.event.CallReservedEvent;
import com.one234gift.saleshistoryservice.salesHistory.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.salesHistory.domain.value.Writer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.saleshistoryservice.salesHistory.command.application.SalesHistoryServiceHelper.getEmployee;

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
    private ApplicationEventPublisher applicationEventPublisher;

    // 외부 모듈
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;

    /**
     * 영업 기록 생성
     */
    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory, Writer writer) {
        // 고객 존재 여부 확인
        Customer customer = customerRepository.getCustomer(registerSalesHistory.getCustomerId());
        verifySaleCustomer(customer);

        // 작성자 정보 조회
        Employee employee = getEmployee(employeeRepository, writer);

        // map
        SalesHistory salesHistory = salesHistoryMapper.mapFrom(registerSalesHistory, customer, employee);
        salesHistoryRepository.save(salesHistory);

        SalesHistoryModel salesHistoryModel = salesHistory.toModel();
        if(salesHistoryModel.existCallReservation()){
            // publish event
            applicationEventPublisher.publishEvent(CallReservedEvent.builder()
                            .callReservationDate(salesHistoryModel.getCallReservationDate())
                            .salesHistoryId(salesHistoryModel.getId())
                            .customer(customer)
                            .reserver(employee.getPhone())
                    .build());
        }
        return salesHistoryModel;
    }

    private void verifySaleCustomer(Customer customer) {
        if(!customer.isSale()){
            throw new IllegalArgumentException("현재 영업중인 고객만 영업 기록을 등록할 수 있습니다.");
        }
    }
}
