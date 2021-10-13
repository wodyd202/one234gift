package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter
@Transactional
public class RegisterSalesHistoryService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private SalesHistoryRepository salesHistoryRepository;

    public RegisterSalesHistoryService(UserRepository userRepository,
                                       CustomerRepository customerRepository,
                                       SalesHistoryRepository salesHistoryRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory) {
        verifyExistCustomer(registerSalesHistory);
        Writer writer = SalesHistoryServiceHelper.findUser(userRepository);

        SalesHistory salesHistory = SalesHistory.register(registerSalesHistory, writer);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    private void verifyExistCustomer(RegisterSalesHistory registerSalesHistory) {
        if(!customerRepository.existByCustomer(registerSalesHistory.getCustomerId())){
            throw new CustomerNotFoundException();
        }
    }
}
