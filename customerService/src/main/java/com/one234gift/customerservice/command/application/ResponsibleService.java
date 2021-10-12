package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.domain.Responsible;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findCustomer;

@Service
@Transactional
public class ResponsibleService {
    private CustomerRepository customerRepository;
    private ResponsiblerRepository responsiblerRepository;

    public ResponsibleService(CustomerRepository customerRepository, ResponsiblerRepository responsiblerRepository) {
        this.customerRepository = customerRepository;
        this.responsiblerRepository = responsiblerRepository;
    }

    public void flag(Long customerId, String manager) {
        findCustomer(customerRepository, customerId);
        Optional<Responsible> findResponsible = responsiblerRepository.findByCustomerIdAndManager(customerId, manager);
        if(findResponsible.isPresent()){
            responsiblerRepository.delete(findResponsible.get());
        }else{
            Responsible responsible = Responsible.of(customerId, manager);
            responsiblerRepository.save(responsible);
        }
    }
}
