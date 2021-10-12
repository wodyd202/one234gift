package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.domain.exception.AlreadySaleStopException;
import com.one234gift.customerservice.domain.model.*;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.one234gift.customerservice.domain.value.SaleState.SALE;
import static com.one234gift.customerservice.domain.value.SaleState.STOP;
import static javax.persistence.EnumType.STRING;

/**
 * 고객(업체)
 */
@Entity
@Table(name = "customer",
        indexes = {
                @Index(name = "customer_category", columnList = "category"),
                @Index(name = "customer_location", columnList = "location"),
                @Index(name = "customer_state", columnList = "saleState")
        })
@DynamicUpdate
public class Customer {

    /**
     * 고객(업체) 고유 번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    /**
     * 고객(업체) 분류(은행, 우체국 등)[필수]
     * # 서버에서 제공하는 분류만 허용함
     */
    @Embedded
    @AttributeOverride(name = "category", column = @Column(name = "category", nullable = false, length = 15))
    private final Category category;

    /**
     * 고객(업체) 정보
     * - 업체명[필수]
     * - 사업자번호
     */
    @Embedded
    private BusinessInfo businessInfo;

    /**
     * 고객(업체) 담당자 정보
     * - 이름[필수]
     * - 이메일
     * - 직위
     */
    @Embedded
    private PurchasingManagers purchasingManagers;

    @Embedded
    @AttributeOverride(name = "tel", column = @Column(name = "fax", length = 13))
    private Tel fax;

    /**
     * 고객(업체) 주소
     * - 지역(서울특별시, 경기도 등)[필수]
     * # 서버에서 제공하는 지역만 허용함
     * - 상세 주소
     */
    @Embedded
    private Address address;

    /**
     * 고객 판매 상태
     * - SALE(판매중)
     * - STOP(판매중지)
     */
    @Enumerated(STRING)
    @Column(nullable = false, length = 4)
    private SaleState saleState;

    /**
     * 고객 생성일
     */
    @Column(nullable = false)
    private final LocalDateTime createDateTime;

    protected Customer(){
        createDateTime = null;
        category = null;
    }

    private Customer(RegisterCustomer registerCustomer) {
        categoryValidation(registerCustomer.getCategory());
        category = new Category(registerCustomer.getCategory());

        businessInfoValidation(registerCustomer.getBusinessInfo());
        businessInfo = new BusinessInfo(registerCustomer.getBusinessInfo());

        purchasingManagerValidation(registerCustomer.getPurchasingManagers());
        purchasingManagers = new PurchasingManagers(this, registerCustomer.getPurchasingManagers());

        if(registerCustomer.getFax() == null){
            this.fax = Tel.getInstance();
        }else{
            this.fax = new Tel(registerCustomer.getFax());
        }

        addressValidation(registerCustomer.getAddress());
        address = new Address(registerCustomer.getAddress());

        createDateTime = LocalDateTime.now();
    }

    private void businessInfoValidation(ChangeBusinessInfo businessInfo) {
        if(businessInfo == null){
            throw new IllegalArgumentException("고객 정보를 입력해주세요.");
        }
    }

    private void purchasingManagerValidation(List<ChangePurchasingManager> purchasingManager) {
        if(purchasingManager == null || purchasingManager.isEmpty()){
            throw new IllegalArgumentException("고객 담당자를 입력해주세요.");
        }
    }

    private void categoryValidation(String category) {
        if(category == null){
            throw new IllegalArgumentException("분류를 입력해주세요.");
        }
    }

    private void addressValidation(ChangeAddress address) {
        if(address == null){
            throw new IllegalArgumentException("고객 주소지 정보를 입력해주세요.");
        }
    }

    public static Customer registerWith(RegisterCustomer registerCustomer) {
        return new Customer(registerCustomer);
    }

    /**
     * @param registerCustomerValidator
     * - 고객 유효성 검사
     * # 고객 생성전 반드시 거쳐야함
     */
    public void register(RegisterCustomerValidator registerCustomerValidator) {
        registerCustomerValidator.validation(category, address);
        saleState = SALE;
    }

    /**
     * @param businessName
     * - 업체명 수정
     */
    public void changeBusinessName(ChangeBusinessName businessName) {
        businessInfo.changeBusinessName(businessName);
    }

    /**
     * @param addressDetail
     * - 업체 상세 주소 수정
     */
    public void changeAddressDetail(ChangeAddressDetail addressDetail) {
        address.changeAddressDetail(addressDetail);
    }

    /**
     * @param businessNumber
     * - 사업자 번호 수정
     */
    public void changeBusinessNumber(ChangeBusinessNumber businessNumber) {
        businessInfo.changeBusinessNumber(businessNumber);
    }

    /**
     * @param fax
     * - 팩스 번호 수정
     */
    public void changeFax(ChangeFax fax) {
        if(fax.getFax() == null){
            this.fax = Tel.getInstance();
        }else{
            this.fax = new Tel(fax.getFax());
        }
    }

    /**
     * - 영업 중지
     */
    public void saleStop() {
        verifySale();
        saleState = STOP;
    }

    private void verifySale() {
        if(saleState.equals(STOP)){
            throw new AlreadySaleStopException();
        }
    }

    public void sale() {
        verifyNotSale();
        saleState = SALE;
    }

    private void verifyNotSale() {
        if(saleState.equals(SALE)){
            throw new AlreadySaleException();
        }
    }

    /**
     * @param purchasingManager
     * - 담당자 추가
     */
    public void addPurchasingManger(ChangePurchasingManager purchasingManager) {
        this.purchasingManagers.add(this, purchasingManager);
    }

    /**
     * @param removePurchasingManager
     * - 담당자 삭제
     */
    public void removePurchasingManager(RemovePurchasingManager removePurchasingManager) {
        this.purchasingManagers.remove(removePurchasingManager);
    }

    public CustomerModel toModel() {
        return CustomerModel.builder()
                .id(id)
                .category(category)
                .businessInfo(businessInfo)
                .purchasingManagers(purchasingManagers)
                .address(address)
                .saleState(saleState)
                .fax(fax)
                .createDateTime(createDateTime)
                .build();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", category=" + category +
                ", businessInfo=" + businessInfo +
                ", purchasingManager=" + purchasingManagers +
                ", address=" + address +
                ", saleState=" + saleState +
                ", createDateTime=" + createDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(category, customer.category) && Objects.equals(businessInfo, customer.businessInfo) && Objects.equals(purchasingManagers, customer.purchasingManagers) && Objects.equals(fax, customer.fax) && Objects.equals(address, customer.address) && saleState == customer.saleState && Objects.equals(createDateTime, customer.createDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, businessInfo, purchasingManagers, fax, address, saleState, createDateTime);
    }
}
