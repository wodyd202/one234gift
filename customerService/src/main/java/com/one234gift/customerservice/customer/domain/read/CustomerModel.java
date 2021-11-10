package com.one234gift.customerservice.customer.domain.read;

import com.one234gift.customerservice.customer.domain.PurchasingManagers;
import com.one234gift.customerservice.customer.command.application.event.*;
import com.one234gift.customerservice.customer.domain.value.*;
import com.one234gift.customerservice.customer.query.application.external.CustomerHistoryModels;
import com.one234gift.customerservice.customer.query.application.external.OrderModels;
import com.one234gift.customerservice.customer.query.application.external.SalesHistoryModels;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerModel {
    private Long id;
    private String category;
    private BusinessInfoModel businessInfo;
    private List<PurchasingManagerModel> purchasingManagers;
    private AddressModel address;
    private SaleState saleState;
    private String fax;

    private LocalDate createDate;

    /**
     * 고객 최근 수정 이력
     */
    private CustomerHistoryModels latelyHistorys;

    /**
     * 최근 고객 메모 이력
     */
    private SalesHistoryModels latelySalesHistorys;

    /**
     * 최근 주문 이력
     */
    private OrderModels latelyOrders;

    /**
     * 고객 담당자
     */
    private List<ResponsibleModel> responsibleModels;

    protected void on(AddedPurchasingManagerEvent event){
        this.purchasingManagers.add(event.getPurchasingManager());
    }

    protected void on(RemovedPurchasingManagerEvent event){
        this.purchasingManagers.remove(event.getId());
    }

    protected void on(ChangedAddressDetailEvent event){
        this.address.changeAddressDetail(event.getAddressDetail());
    }

    protected void on(ChangedBusinessNameEvent event){
        this.businessInfo.changeBusinessName(event.getBusinessName());
    }

    protected void on(ChangedBusinessNumberEvent event){
        this.businessInfo.changeBusinessNumber(event.getBusinessNumber());
    }

    protected void on(ChangedFaxEvent event){
        this.fax = event.getFax();
    }

    protected void on(ChangedStateEvent event){
        this.saleState = event.getState();
    }

    public void addLatelyHistorys(CustomerHistoryModels latelyHistorys) {
        this.latelyHistorys = latelyHistorys;
    }

    public void addLatelySalesHistorys(SalesHistoryModels latelySalesHistorys) { this.latelySalesHistorys = latelySalesHistorys;}

    public void addLatelyOrders(OrderModels latelyOrders) { this.latelyOrders = latelyOrders; }

    public void addResponsibleUsers(List<ResponsibleModel> responsibleUsers) {
        this.responsibleModels = responsibleUsers;
    }

    @Builder
    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         PurchasingManagers purchasingManagers,
                         Address address,
                         SaleState saleState,
                         Tel fax,
                         LocalDate createDate) {
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.purchasingManagers = purchasingManagers.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.fax = fax.get();
        this.createDate = createDate;
    }

    public CustomerModel(Long id,
                         String category,
                         BusinessInfoModel businessInfo,
                         List<PurchasingManagerModel> purchasingManagers,
                         AddressModel address,
                         String fax,
                         LocalDate createDate) {
        this.id = id;
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.saleState = SaleState.SALE;
        this.fax = fax;
        this.createDate = createDate;

    }

    /**
     * 고객 상세 조회시 사용
     */
    public CustomerModel(Long id,
                         String category,
                         BusinessInfoModel businessInfo,
                         List<PurchasingManagerModel> purchasingManagers,
                         AddressModel address,
                         String fax){
        this.id = id;
        this.category = category;
        this.businessInfo = businessInfo;
        this.purchasingManagers = purchasingManagers;
        this.address = address;
        this.fax = fax;
        this.createDate = LocalDate.now();
        this.saleState = SaleState.SALE;
    }

    /**
     * 고객 리스트 조회시 사용
     */
    public CustomerModel(Long id,
                         Category category,
                         BusinessName businessName,
                         Location location,
                         SaleState saleState) {
        this.id = id;
        this.category = category.get();
        this.businessInfo = BusinessInfoModel.builder()
                                .name(businessName.get())
                                .build();
        this.address = AddressModel.builder()
                                .location(location.get())
                                .build();
        this.saleState = saleState;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", businessInfo=" + businessInfo +
                ", purchasingManagers=" + purchasingManagers +
                ", address=" + address +
                ", saleState=" + saleState +
                ", fax='" + fax + '\'' +
                ", createDate=" + createDate +
                ", latelyHistorys=" + latelyHistorys +
                ", latelySalesHistorys=" + latelySalesHistorys +
                ", latelyOrders=" + latelyOrders +
                ", responsibleModels=" + responsibleModels +
                '}';
    }
}
