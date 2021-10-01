package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.model.PurchasingManagerModel;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Embeddable
public class PurchasingManagers {
    @OneToMany(mappedBy = "customer", fetch = LAZY, cascade = ALL)
    private final List<PurchasingManager> purchasingManagers = new ArrayList<>();

    protected PurchasingManagers(){}

    public PurchasingManagers(Customer customer, List<ChangePurchasingManager> purchasingManagers) {
        purchasingManagers.stream().forEach(purchasingManager -> add(customer, purchasingManager));
    }

    public void add(Customer customer, ChangePurchasingManager purchasingManager){
        purchasingManagers.add(new PurchasingManager(purchasingManager, customer));
    }

    public List<PurchasingManagerModel> toModel(){
        return purchasingManagers.stream().map(purchasingManager->purchasingManager.toModel()).collect(toList());
    }

}
