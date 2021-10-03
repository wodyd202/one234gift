package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.value.Manager;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findById;

@Service
@Transactional
@Setter
public class ChangeSaleStateService {
    private CustomerRepository customerRepository;

    public ChangeSaleStateService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saleStop(Long id, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.saleStop();
        customerRepository.save(customer);
    }

    public void sale(Long id, Manager manager) {
        Customer customer = findById(customerRepository, id);
        customer.sale();
        customerRepository.save(customer);
    }
}
