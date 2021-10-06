package com.one234gift.saleshistoryservice.command.application;

import com.one234gift.saleshistoryservice.command.application.exception.CustomerNotFoundException;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.one234gift.saleshistoryservice.domain.model.RegisterSalesHistory;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.domain.value.Writer;
import lombok.Setter;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter
@Transactional
public class RegisterSalesHistoryService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private SalesHistoryRepository salesHistoryRepository;
    private CircuitBreakerFactory circuitBreakerFactory;

    public RegisterSalesHistoryService(UserRepository userRepository,
                                       CustomerRepository customerRepository,
                                       SalesHistoryRepository salesHistoryRepository,
                                       CircuitBreakerFactory circuitBreakerFactory) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.salesHistoryRepository = salesHistoryRepository;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public SalesHistoryModel register(RegisterSalesHistory registerSalesHistory) {
        verifyExistCustomer(registerSalesHistory);
        Writer writer = findUser();

        SalesHistory salesHistory = SalesHistory.register(registerSalesHistory, writer);
        salesHistoryRepository.save(salesHistory);
        return salesHistory.toModel();
    }

    private void verifyExistCustomer(RegisterSalesHistory registerSalesHistory) {
        CircuitBreaker customerAPICircuit = circuitBreakerFactory.create("customerAPICircuit");
        Boolean existCustomer = customerAPICircuit.run(() -> customerRepository.existByCustomer(registerSalesHistory.getCustomerId()), e -> false);

        if(!customerRepository.existByCustomer(registerSalesHistory.getCustomerId())){
            throw new CustomerNotFoundException();
        }
    }

    private Writer findUser(){
        CircuitBreaker userAPICircuit = circuitBreakerFactory.create("userAPICircuit");
//        return userAPICircuit.run(()->SalesHistoryServiceHelper.findUser(userRepository),e-> Writer.builder().build());
        return SalesHistoryServiceHelper.findUser(userRepository);
    }
}
