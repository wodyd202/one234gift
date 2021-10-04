package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedStateEvent;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.value.Manager;
import com.one234gift.customerservice.domain.value.SaleState;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findCustomer;

@Service
@Transactional
@Setter
public class ChangeSaleStateService {
    private UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ChangeSaleStateService(UserRepository userRepository, CustomerRepository customerRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void saleStop(Long id) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.saleStop();
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedStateEvent(SaleState.SALE,id,manager));
    }

    public void sale(Long id) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.sale();
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedStateEvent(SaleState.STOP,id,manager));
    }
}
