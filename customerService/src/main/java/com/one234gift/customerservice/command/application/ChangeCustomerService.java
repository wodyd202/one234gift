package com.one234gift.customerservice.command.application;

import com.one234gift.customerservice.command.application.event.*;
import com.one234gift.customerservice.command.application.exception.DataBaseNotAccessAbleException;
import com.one234gift.customerservice.command.application.external.UserRepository;
import com.one234gift.customerservice.domain.model.*;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.read.PurchasingManagerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@Service
@Transactional
@Retryable(maxAttempts = 3, include = Exception.class, backoff = @Backoff(delay = 1000))
public class ChangeCustomerService extends AbstractChangeCustomerService{
    public ChangeCustomerService(UserRepository userRepository, CustomerRepository customerRepository, ApplicationEventPublisher applicationEventPublisher) {
        super(userRepository, customerRepository, applicationEventPublisher);
    }

    /**
     * 상호명 변경
     *
     * @param customerId    고객 고유 번호
     * @param businessName  변경할 상호명
     */
    private final String BUSINESS_NAME = "business name";
    public CustomerModel changeBusinessName(Long customerId, ChangeBusinessName businessName) {
        return action((customer, manager) -> {
            customer.changeBusinessName(businessName);
            return new ChangedBusinessNameEvent(businessName.getName(), customerId, manager);
        }, customerId, BUSINESS_NAME);
    }

    /**
     * 고객 상세주소 변경
     *
     * @param customerId    고객 고유 번호
     * @param addressDetail 변경할 상세주소
     */
    private final String ADDRESS_DETAIL = "address detail";
    public CustomerModel changeAddressDetail(Long customerId, ChangeAddressDetail addressDetail) {
        return action((customer, manager) -> {
            customer.changeAddressDetail(addressDetail);
            return new ChangedAddressDetailEvent(addressDetail.getDetail(), customerId, manager);
        }, customerId, ADDRESS_DETAIL);
    }

    /**
     * 사업자번호 변경
     *
     * @param customerId        고객 고유 번호
     * @param businessNumber    변경할 사업자번호
     */
    private final String BUSIESS_NUMBER = "business number";
    public CustomerModel changeBusinessNumber(Long customerId, ChangeBusinessNumber businessNumber) {
        return action((customer, manager) -> {
            customer.changeBusinessNumber(businessNumber);
            return new ChangedBusinessNumberEvent(businessNumber.getBusinessNumber(), customerId, manager);
        }, customerId, BUSIESS_NUMBER);
    }

    /**
     * 팩스 변경
     *
     * @param customerId    고객 고유 번호
     * @param fax           변경할 팩스번호
     */
    private final String FAX = "fax";
    public CustomerModel changeFax(Long customerId, ChangeFax fax) {
        return action((customer, manager) -> {
            customer.changeFax(fax);
            return new ChangedFaxEvent(fax.getFax(), customerId, manager);
        }, customerId, "fax");
    }

    /**
     * 구매담당자 추가
     *
     * @param customerId            고객 고유 번호
     * @param purchasingManager     구매담당자 정보
     */
    private final String PURCHASING_MANAGER = "purchasing manager";
    public CustomerModel addPurchasingManager(Long customerId, ChangePurchasingManager purchasingManager) {
        return action((customer, manager) -> {
            customer.addPurchasingManger(purchasingManager);
            return new AddedPurcgasubgManager(purchasingManager, customerId, manager);
        }, customerId, PURCHASING_MANAGER);
    }

    /**
     * 구매담당자 삭제
     *
     * @param customerId            고객 고유 번호
     * @param purchasingManager     구매담당자 정보
     * @return
     */
    public CustomerModel removePurchasingManager(Long customerId, RemovePurchasingManager purchasingManager){
        return action((customer, manager) -> {
            PurchasingManagerModel purchasingManagerModel = customer.removePurchasingManager(purchasingManager).toModel();
            return new RemovedPurcgasubgManager(purchasingManagerModel, customerId, manager);
        }, customerId, PURCHASING_MANAGER);
    }

    @Recover
    private void retryFallback(Exception e){
        e.printStackTrace();
        log.error(e.toString());
        throw new DataBaseNotAccessAbleException();
    }
}
