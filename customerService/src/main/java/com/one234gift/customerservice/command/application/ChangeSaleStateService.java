package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedStateEvent;
import com.one234gift.customerservice.domain.value.SaleState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
@Retryable(maxAttempts = 3, include = SQLException.class, backoff = @Backoff(delay = 500))
public class ChangeSaleStateService extends AbstractChangeCustomerService{

    public ChangeSaleStateService(UserRepository userRepository, CustomerRepository customerRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(userRepository, customerRepository, applicationEventPublisher);
    }

    /**
     * 판매 중단
     *
     * @param customerId    고객 고유 번호
     */
    private final String SALE_STATE = "sale state";
    public void saleStop(Long customerId) {
        action((customer, manager)->{
            customer.saleStop();
            return new ChangedStateEvent(SaleState.SALE,customerId,manager);
        }, customerId, SALE_STATE);
    }

    /**
     * 판매중으로 변경
     *
     * @param customerId
     */
    public void sale(Long customerId) {
        action((customer, manager)->{
            customer.sale();
            return new ChangedStateEvent(SaleState.STOP,customerId,manager);
        }, customerId, SALE_STATE);
    }
}
