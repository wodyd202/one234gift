package com.one234gift.customerservice.customer.query.application;

import com.one234gift.customerservice.customer.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.customer.command.application.event.RegisteredCustomerEvent;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 고객 이벤트 리스너
 */
@Async("queryCustomerExecutor")
@Component
@Slf4j
public class QueryCustomerEventListener {
    @Autowired
    private QueryCustomerRepository customerRepository;

    @EventListener
    public void handle(RegisteredCustomerEvent event){
        CustomerModel customerModel = new CustomerModel(event.getId(),
                event.getCategory(),
                event.getBusinessInfo(),
                event.getPurchasingManagers(),
                event.getAddress(),
                event.getFax(),
                event.getCreateDate());
        customerRepository.save(customerModel);
        log.info("save customer into redis store : {}", customerModel);
    }

    private final String ON = "on";
    @EventListener
    public void handle(ChangedCustomerEvent changedCustomerEvent) throws Exception{
        CustomerModel customerModel = customerRepository.findById(changedCustomerEvent.getId()).get();
        invoke(changedCustomerEvent, customerModel);
        customerRepository.save(customerModel);
        log.info("change customer into redis store : {}", customerModel);
    }

    private void invoke(ChangedCustomerEvent changedCustomerEvent, CustomerModel customerModel) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = CustomerModel.class.getDeclaredMethod(ON, changedCustomerEvent.getClass());
        method.setAccessible(true);
        method.invoke(customerModel, changedCustomerEvent);
    }
}
