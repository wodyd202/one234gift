package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedBusinessNameEvent;
import com.one234gift.customerservice.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.domain.CustomerHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
public class CustomerHistoryEventListener {
    private final CustomerHistoryRepository customerHistoryRepository;

    public CustomerHistoryEventListener(CustomerHistoryRepository customerHistoryRepository) {
        this.customerHistoryRepository = customerHistoryRepository;
    }

    @EventListener
    protected void handle(ChangedCustomerEvent event){
        CustomerHistory customerHistory = CustomerHistory.of(event);
        customerHistoryRepository.save(customerHistory);
        log.info("save customer history : {}" , customerHistory);
    }
}
