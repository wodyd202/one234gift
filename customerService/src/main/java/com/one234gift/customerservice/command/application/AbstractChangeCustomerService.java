package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.Manager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findUser;

@Slf4j
@AllArgsConstructor
abstract public class AbstractChangeCustomerService {
    protected UserRepository userRepository;
    protected final CustomerRepository customerRepository;
    protected final ApplicationEventPublisher applicationEventPublisher;

    // 공통 코드 분리
    protected CustomerModel action(Action action, Long customerId, String changeType){
        Manager manager = findUser(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, customerId);
        ChangedCustomerEvent event = action.process(customer, manager);
        customerRepository.save(customer);
        log.info("change customer {} : {}",changeType, customer);
        applicationEventPublisher.publishEvent(event);
        return customer.toModel();
    }

    @FunctionalInterface
    protected interface Action {
        ChangedCustomerEvent process(Customer customer, Manager manager);
    }
}
