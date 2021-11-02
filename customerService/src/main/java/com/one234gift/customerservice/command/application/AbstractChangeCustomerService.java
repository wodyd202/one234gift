package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.command.application.event.UpdatedCustomerEvent;
import com.one234gift.customerservice.command.application.external.UserRepository;
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
        // getDatas
        Manager manager = findUser(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, customerId);

        // process
        ChangedCustomerEvent event = action.process(customer, manager);

        // save
        customerRepository.save(customer);
        log.info("change customer {} : {}",changeType, customer);

        // publish event
        CustomerModel customerModel = customer.toModel();
        applicationEventPublisher.publishEvent(event);
        applicationEventPublisher.publishEvent(new UpdatedCustomerEvent(customerModel));
        return customerModel;
    }

    @FunctionalInterface
    protected interface Action {
        ChangedCustomerEvent process(Customer customer, Manager manager);
    }
}
