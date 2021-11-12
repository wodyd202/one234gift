package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.event.ChangedStateEvent;
import com.one234gift.customerservice.customer.command.application.external.Employee;
import com.one234gift.customerservice.customer.command.application.external.EmployeeRepository;
import com.one234gift.customerservice.customer.command.application.util.ProcessUserIdGetter;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.value.SaleState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.customer.command.application.CustomerServiceHelper.getCustomer;
import static com.one234gift.customerservice.customer.command.application.CustomerServiceHelper.getEmployee;

/**
 * 고객 상태 변경 서비스
 */
@Service
@Transactional
@Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 500))
@AllArgsConstructor
@Slf4j
public class ChangeSaleStateService {
    private CustomerRepository customerRepository;
    private ProcessUserIdGetter userIdGetter;
    private ApplicationEventPublisher applicationEventPublisher;

    // 외부 모듈
    private EmployeeRepository userRepository;

    /**
     * @param customerId    고객 고유 번호
     * # 판매 중단
     */
    public CustomerModel saleStop(Long customerId) {
        Customer customer = getCustomer(customerRepository, customerId);
        Employee employee = getEmployee(userRepository, userIdGetter);

        customer.saleStop();
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("sale stop customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedStateEvent(customerId,employee.getName(), SaleState.STOP));
        return customerModel;
    }

    /**
     * @param customerId
     * # 판매중으로 변경
     */
    public CustomerModel sale(Long customerId) {
        Customer customer = getCustomer(customerRepository, customerId);
        Employee employee = getEmployee(userRepository, userIdGetter);

        customer.sale();
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("sale customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedStateEvent(customerId,employee.getName(), SaleState.SALE));
        return customerModel;
    }
}
