package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.model.RemovePurchasingManager;
import com.one234gift.customerservice.domain.read.PurchasingManagerModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class PurchasingManagers {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "purchasing_managers", joinColumns = @JoinColumn(name = "id"))
    private final List<PurchasingManager> purchasingManagers = new ArrayList<>();

    protected PurchasingManagers(){}

    public PurchasingManagers(Customer customer, List<ChangePurchasingManager> purchasingManagers) {
        purchasingManagers.stream().forEach(purchasingManager -> {
                add(customer, purchasingManager);
        });
    }

    public void add(Customer customer, ChangePurchasingManager purchasingManager){
        purchasingManagers.add(new PurchasingManager(purchasingManager, customer));
    }

    public void remove(RemovePurchasingManager removePurchasingManager) {
        for (PurchasingManager purchasingManager : purchasingManagers) {
            if(purchasingManager.isTarget(removePurchasingManager)){
                purchasingManagers.remove(purchasingManager);
                return;
            }
        }
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
