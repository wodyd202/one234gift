package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.customerservice.domain.model.RegisterSalesHistory;
import com.one234gift.customerservice.domain.SalesHistory;
import com.one234gift.customerservice.domain.read.SalesHistoryModel;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.Manager;
import com.one234gift.customerservice.domain.value.SaleState;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.command.application.CustomerServiceHelper.findCustomer;

@Service
@Setter
@Transactional
public class RegisterSalesHistoryService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private final SalesHistoryRepository salesHistoryRepository;

    public RegisterSalesHistoryService(UserRepository userRepository, CustomerRepository customerRepository, SalesHistoryRepository salesHistoryRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public SalesHistoryModel register(Long customerId, RegisterSalesHistory registerSalesHistory) {
        Manager manager = findCustomer(userRepository);
        CustomerModel customer = findCustomer(customerRepository, customerId).toModel();
        if(customer.getSaleState().equals(SaleState.STOP)){
            throw new CustomerNotFoundException();
        }
        SalesHistory salesHistory = SalesHistory.register(customerId, registerSalesHistory, manager);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }
}
