package com.one234gift.customerservice.domain.read;

import com.one234gift.customerservice.domain.value.*;
import com.one234gift.customerservice.query.application.external.CustomerHistoryModels;
import com.one234gift.customerservice.query.application.external.SalesHistoryModels;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    private LocalDateTime createDateTime;

    /**
     * 고객 최근 수정 이력
     */
    private CustomerHistoryModels latelyHistorys;

    /**
     * 최근 고객 메모 이력
     */
    private SalesHistoryModels latelySalesHistorys;

    public void addLatelyHistorys(CustomerHistoryModels latelyHistorys) {
        this.latelyHistorys = latelyHistorys;
    }

    public void addLatelySalesHistorys(SalesHistoryModels latelySalesHistorys) { this.latelySalesHistorys = latelySalesHistorys;}

    @Builder
    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         PurchasingManagers purchasingManagers,
                         Address address,
                         SaleState saleState,
                         Tel fax,
                         LocalDateTime createDateTime
                         ){
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.purchasingManagers = purchasingManagers.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.fax = fax.get();
        this.createDateTime = createDateTime;
    }

    @Builder
    public CustomerModel(Long id,
                         Category category,
                         BusinessInfo businessInfo,
                         Address address,
                         SaleState saleState,
                         Tel fax,
                         LocalDateTime createDateTime) {
        this.id = id;
        this.category = category.get();
        this.businessInfo = businessInfo.toModel();
        this.address = address.toModel();
        this.saleState = saleState;
        this.fax = fax.get();
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", businessInfo=" + businessInfo +
                ", purchasingManagers=" + Arrays.toString(purchasingManagers.toArray()) +
                ", address=" + address +
                ", saleState=" + saleState +
                ", fax='" + fax + '\'' +
                ", createDateTime=" + createDateTime +
                ", latelyHistorys=" + latelyHistorys +
                '}';
    }
}
