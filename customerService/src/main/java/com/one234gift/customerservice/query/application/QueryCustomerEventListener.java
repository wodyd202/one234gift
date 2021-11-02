package com.one234gift.customerservice.query.application;

import com.one234gift.customerservice.command.application.event.RegisteredCustomerEvent;
import com.one234gift.customerservice.command.application.event.UpdatedCustomerEvent;
import com.one234gift.customerservice.domain.read.CustomerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueryCustomerEventListener {
    @Autowired
    private QueryCustomerRepository customerRepository;

    @EventListener
    public void handle(RegisteredCustomerEvent event){
        CustomerModel customerModel = event.getCustomer();
        customerRepository.save(customerModel);
        log.info("save customer into redis store : {}", customerModel);
    }

    @EventListener
    public void handle(UpdatedCustomerEvent event){
        CustomerModel customerModel = event.getCustomer();
        customerRepository.save(customerModel);
        log.info("change customer into redis store : {}", customerModel);
    }
}
