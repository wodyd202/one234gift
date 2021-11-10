package com.one234gift.customerservice.customer.command.application;

import com.one234gift.customerservice.customer.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.customer.command.application.external.UserRepository;
import com.one234gift.customerservice.customer.command.application.model.*;
import com.one234gift.customerservice.customer.command.application.util.ProcessUserIdGetter;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.command.application.event.*;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.value.*;
import com.one234gift.customerservice.customer.query.application.external.ManagerModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.one234gift.customerservice.customer.command.application.CustomerServiceHelper.findCustomer;
import static com.one234gift.customerservice.customer.command.application.CustomerServiceHelper.findUser;

@Slf4j
@Service
@Transactional
@Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 500))
@AllArgsConstructor
public class ChangeCustomerService {
    private CustomerMapper customerMapper;
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private ProcessUserIdGetter userIdGetter;
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * @param customerId    고객 고유 번호
     * @param businessName  변경할 상호명
     * # 상호명 변경
     */
    public CustomerModel changeBusinessName(Long customerId, ChangeBusinessName businessName) {
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository,userIdGetter).toModel();

        customer.changeBusinessName(new BusinessName(businessName.getName()));
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("change businessName of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedBusinessNameEvent(customerId, updater.getName(), businessName.getName()));
        return customerModel;
    }

    /**
     * @param customerId          고객 고유 번호
     * @param changeAddressDetail 변경할 상세주소
     * # 고객 상세주소 변경
     */
    public CustomerModel changeAddressDetail(Long customerId, ChangeAddressDetail changeAddressDetail) {
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository,userIdGetter).toModel();

        customer.changeAddressDetail(getAddressDetail(changeAddressDetail));
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("change addressDetail of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedAddressDetailEvent(customerId, updater.getName(), changeAddressDetail.getDetail()));
        return customerModel;
    }

    private AddressDetail getAddressDetail(ChangeAddressDetail changeAddressDetail) {
        return changeAddressDetail.getDetail() == null ? AddressDetail.getInstance() : new AddressDetail(changeAddressDetail.getDetail());
    }

    /**
     * @param customerId        고객 고유 번호
     * @param changeBusinessNumber    변경할 사업자번호
     * # 사업자 번호 변경
     */
    public CustomerModel changeBusinessNumber(Long customerId, ChangeBusinessNumber changeBusinessNumber) {
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository, userIdGetter).toModel();

        customer.changeBusinessNumber(getBusinessNumber(changeBusinessNumber));
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("change businessNumber of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedBusinessNumberEvent(customerId,updater.getName(), changeBusinessNumber.getBusinessNumber()));
        return customerModel;
    }

    private BusinessNumber getBusinessNumber(ChangeBusinessNumber changeBusinessNumber) {
        return changeBusinessNumber.getBusinessNumber() == null ? BusinessNumber.getInstance() : new BusinessNumber(changeBusinessNumber.getBusinessNumber());
    }

    /**
     * @param customerId    고객 고유 번호
     * @param changeFax     변경할 팩스번호
     * # 팩스 변경
     */
    public CustomerModel changeFax(Long customerId, ChangeFax changeFax) {
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository, userIdGetter).toModel();

        customer.changeFax(getFax(changeFax));
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("change fax of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new ChangedFaxEvent(customerId, updater.getName(), changeFax.getFax()));
        return customerModel;
    }

    private Tel getFax(ChangeFax changeFax) {
        return changeFax.getFax() == null ? Tel.getInstance() : new Tel(changeFax.getFax());
    }

    /**
     * @param customerId            고객 고유 번호
     * @param addPurchasingManager     구매담당자 정보
     * # 구매담당자 추가
     */
    public CustomerModel addPurchasingManager(Long customerId, ChangePurchasingManager addPurchasingManager) {
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository, userIdGetter).toModel();

        // map
        PurchasingManager purchasingManager = customerMapper.mapFrom(addPurchasingManager);
        customer.addPurchasingManger(purchasingManager);
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("add purchasingManager of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new AddedPurchasingManagerEvent(customerId,updater.getName(), purchasingManager.toModel()));
        return customerModel;
    }

    /**
     * @param customerId            고객 고유 번호
     * @param purchasingManager     구매담당자 정보
     * # 구매담당자 삭제
     */
    public CustomerModel removePurchasingManager(Long customerId, RemovePurchasingManager purchasingManager){
        Customer customer = findCustomer(customerRepository, customerId);
        ManagerModel updater = findUser(userRepository, userIdGetter).toModel();

        customer.removePurchasingManager(purchasingManager.getId());
        customerRepository.save(customer);
        CustomerModel customerModel = customer.toModel();
        log.info("remove purchasingManager of customer into database : {}", customerModel);

        // publish event
        applicationEventPublisher.publishEvent(new RemovedPurchasingManagerEvent(customerId,updater.getName(), purchasingManager.getId()));
        return customerModel;
    }

    @Recover
    private void retryFallback(Exception e){
        e.printStackTrace();
        log.error(e.toString());
        throw new DataBaseNotAccessAbleException();
    }
}
