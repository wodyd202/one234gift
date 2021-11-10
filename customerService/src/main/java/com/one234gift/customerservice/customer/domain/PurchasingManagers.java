package com.one234gift.customerservice.customer.domain;

import com.one234gift.customerservice.customer.domain.exception.PurchasingManagerNotFoundException;
import com.one234gift.customerservice.customer.domain.read.PurchasingManagerModel;
import com.one234gift.customerservice.customer.domain.value.PurchasingManager;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;

/**
 * 구매 담당자
 */
@Embeddable
public class PurchasingManagers {

    @OneToMany(mappedBy = "customer", cascade = {ALL}, orphanRemoval = true)
    private final List<PurchasingManager> purchasingManagers = new ArrayList<>();

    public static PurchasingManagers getInstance() {
        return new PurchasingManagers();
    }

    protected PurchasingManagers(){}

    /**
     * @param purchasingManagers
     * @param customer
     * 구매담당자 리스트 추가
     */
    protected void addAll(List<PurchasingManager> purchasingManagers, Customer customer){
        this.purchasingManagers.addAll(purchasingManagers);
        purchasingManagers.forEach(purchasingManager -> purchasingManager.addCustomer(customer));
    }

    /**
     * @param purchasingManager
     * @param customer
     * # 구매담당자 추가
     */
    protected void add(PurchasingManager purchasingManager, Customer customer){
        purchasingManagers.add(purchasingManager);
        purchasingManager.addCustomer(customer);
    }

    /**
     * @param removePurchasingManager
     * # 구매담당자 제거
     */
    protected PurchasingManager remove(long removePurchasingManager) {
        for (PurchasingManager purchasingManager : purchasingManagers) {
            if(purchasingManager.isTarget(removePurchasingManager)){
                purchasingManagers.remove(purchasingManager);
                return purchasingManager;
            }
        }
        throw new PurchasingManagerNotFoundException();
    }

    public List<PurchasingManagerModel> toModel(){
        return purchasingManagers.stream().map(purchasingManager->purchasingManager.toModel()).collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasingManagers that = (PurchasingManagers) o;
        return Objects.equals(purchasingManagers, that.purchasingManagers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasingManagers);
    }
}
