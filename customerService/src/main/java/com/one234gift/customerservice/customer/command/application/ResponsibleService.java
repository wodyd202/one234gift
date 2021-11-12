package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.customer.domain.value.Responsible;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.one234gift.customerservice.customer.command.application.CustomerServiceHelper.getCustomer;

/**
 * 고객 담당 서비스
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
@Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 1000))
public class ResponsibleService {
    private CustomerRepository customerRepository;
    private ResponsiblerRepository responsiblerRepository;

    public void flag(Long customerId, String manager) {
        getCustomer(customerRepository, customerId);
        Optional<Responsible> findResponsible = responsiblerRepository.findByCustomerIdAndManager(customerId, manager);
        if(findResponsible.isPresent()){
            responsiblerRepository.delete(findResponsible.get());
        }else{
            Responsible responsible = Responsible.of(customerId, manager);
            responsiblerRepository.save(responsible);
        }
    }

    @Recover
    private void retryFallback(Exception e){
        e.printStackTrace();
        log.error(e.toString());
        throw new DataBaseNotAccessAbleException();
    }
}
