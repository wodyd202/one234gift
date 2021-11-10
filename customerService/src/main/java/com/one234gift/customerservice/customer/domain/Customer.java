package com.one234gift.customerservice.customer.domain;

import com.one234gift.customerservice.customer.domain.exception.AlreadySaleException;
import com.one234gift.customerservice.customer.domain.exception.AlreadySaleStopException;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.value.*;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.one234gift.customerservice.customer.domain.value.SaleState.SALE;
import static com.one234gift.customerservice.customer.domain.value.SaleState.STOP;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

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
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * 고객(업체) 분류(은행, 우체국 등)[필수]
     * # 서버에서 제공하는 분류만 허용함
     */
    @Embedded
    @AttributeOverride(name = "category", column = @Column(name = "category", nullable = false, length = 15))
    private Category category;

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
    private final LocalDate createDate;

    @Version
    private long version;

    protected Customer(){
        createDate = null;
        category = null;
    }

    @Builder
    public Customer(Category category,
                    BusinessInfo businessInfo,
                    Tel fax,
                    Address address) {
        setCategory(category);
        setBusinessInfo(businessInfo);
        setFax(fax);
        setAddress(address);
        this.purchasingManagers = PurchasingManagers.getInstance();
        this.createDate = LocalDate.now();
    }

    private void setCategory(Category category) {
        if(category == null){
            throw new IllegalArgumentException("분류를 입력해주세요.");
        }
        this.category = category;
    }

    private void setBusinessInfo(BusinessInfo businessInfo) {
        if(businessInfo == null){
            throw new IllegalArgumentException("고객 정보를 입력해주세요.");
        }
        this.businessInfo = businessInfo;
    }

    private void setFax(Tel fax) {
        if(fax == null){
            this.fax = Tel.getInstance();
            return;
        }
        this.fax = fax;
    }

    private void setAddress(Address address) {
        if(address == null){
            throw new IllegalArgumentException("고객 주소지 정보를 입력해주세요.");
        }
        this.address = address;
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
    public void changeBusinessName(BusinessName businessName) {
        businessInfo.changeBusinessName(businessName);
    }

    /**
     * @param addressDetail
     * - 업체 상세 주소 수정
     */
    public void changeAddressDetail(AddressDetail addressDetail) {
        address.changeAddressDetail(addressDetail);
    }

    /**
     * @param businessNumber
     * - 사업자 번호 수정
     */
    public void changeBusinessNumber(BusinessNumber businessNumber) {
        businessInfo.changeBusinessNumber(businessNumber);
    }

    /**
     * @param fax
     * - 팩스 번호 수정
     */
    public void changeFax(Tel fax) {
        if(fax == null){
            this.fax = Tel.getInstance();
            return;
        }
        this.fax = fax;
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
     * @param purchasingManagers
     * - 담당자 리스트 추가
     */
    public void addPurchasingMangers(List<PurchasingManager> purchasingManagers) {
        this.purchasingManagers.addAll(purchasingManagers, this);
    }

    /**
     * @param purchasingManager
     * - 담당자 추가
     */
    public void addPurchasingManger(PurchasingManager purchasingManager) {
        this.purchasingManagers.add(purchasingManager, this);
    }

    /**
     * @param purchasingManagerIdx
     * - 담당자 삭제
     */
    public PurchasingManager removePurchasingManager(long purchasingManagerIdx) {
        return this.purchasingManagers.remove(purchasingManagerIdx);
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
                .createDate(createDate)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(category, customer.category) && Objects.equals(businessInfo, customer.businessInfo) && Objects.equals(purchasingManagers, customer.purchasingManagers) && Objects.equals(fax, customer.fax) && Objects.equals(address, customer.address) && saleState == customer.saleState && Objects.equals(createDate, customer.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, businessInfo, purchasingManagers, fax, address, saleState, createDate);
    }

    public Long getId() {
        return id;
    }
}
