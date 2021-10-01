package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.model.ChangeAddressDetail;
import com.one234gift.customerservice.domain.model.ChangeBusinessName;
import com.one234gift.customerservice.domain.model.ChangeBusinessNumber;
import com.one234gift.customerservice.domain.model.ChangeFax;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findById;

@Service
@Transactional
public class ChangeCustomerService {
    private final CustomerRepository customerRepository;

    public ChangeCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel changeBusinessName(Long id, ChangeBusinessName businessName, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.changeBusinessName(businessName);
        customerRepository.save(customer);
        return customer.toModel();
    }

    public CustomerModel changeAddressDetail(Long id, ChangeAddressDetail addressDetail, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.changeAddressDetail(addressDetail);
        customerRepository.save(customer);
        return customer.toModel();
    }

    public CustomerModel changeBusinessNumber(Long id, ChangeBusinessNumber businessNumber, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.changeBusinessNumber(businessNumber);
        customerRepository.save(customer);
        return customer.toModel();
    }

    public CustomerModel changeFax(Long id, ChangeFax fax, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.changeFax(fax);
        customerRepository.save(customer);
        return customer.toModel();
    }
}
