package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.ChangedAddressDetailEvent;
import com.one234gift.customerservice.command.application.event.ChangedBusinessNameEvent;
import com.one234gift.customerservice.command.application.event.ChangedBusinessNumberEvent;
import com.one234gift.customerservice.command.application.event.ChangedFaxEvent;
import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.model.ChangeAddressDetail;
import com.one234gift.customerservice.domain.model.ChangeBusinessName;
import com.one234gift.customerservice.domain.model.ChangeBusinessNumber;
import com.one234gift.customerservice.domain.model.ChangeFax;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.Manager;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findCustomer;

@Service
@Transactional
@Setter
public class ChangeCustomerService {
    private final CustomerRepository customerRepository;
    private UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ChangeCustomerService(CustomerRepository customerRepository, UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public CustomerModel changeBusinessName(Long id, ChangeBusinessName businessName) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.changeBusinessName(businessName);
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedBusinessNameEvent(businessName.getName(), id, manager));
        return customer.toModel();
    }

    public CustomerModel changeAddressDetail(Long id, ChangeAddressDetail addressDetail) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.changeAddressDetail(addressDetail);
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedAddressDetailEvent(addressDetail.getDetail(), id, manager));
        return customer.toModel();
    }

    public CustomerModel changeBusinessNumber(Long id, ChangeBusinessNumber businessNumber) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.changeBusinessNumber(businessNumber);
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedBusinessNumberEvent(businessNumber.getBusinessNumber(), id, manager));
        return customer.toModel();
    }

    public CustomerModel changeFax(Long id, ChangeFax fax) {
        Manager manager = findCustomer(userRepository);
        Customer customer = CustomerServiceHelper.findCustomer(customerRepository, id);
        customer.changeFax(fax);
        customerRepository.save(customer);
        applicationEventPublisher.publishEvent(new ChangedFaxEvent(fax.getFax(), id, manager));
        return customer.toModel();
    }
}
