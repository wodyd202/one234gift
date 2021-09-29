package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.domain.model.PurchasingManagerModel;

import java.util.Objects;

public class PurchasingManager {
    private ManagerName name;
    private Email email;
    private JobTitle jobTitle;

    public PurchasingManager(ChangePurchasingManager purchasingManager) {
        setManagerName(purchasingManager);
        setEmail(purchasingManager);
        setJobTitle(purchasingManager);
    }

    private void setManagerName(ChangePurchasingManager purchasingManager) {
        nameValidation(purchasingManager.getName());
        name = new ManagerName(purchasingManager.getName());
    }

    private void setEmail(ChangePurchasingManager purchasingManager) {
        if(purchasingManager.getEmail() != null){
            email = new Email(purchasingManager.getEmail());
        }else{
            email = new Email();
        }
    }

    private void setJobTitle(ChangePurchasingManager purchasingManager) {
        if(purchasingManager.getJobTitle() != null){
            jobTitle = new JobTitle(purchasingManager.getJobTitle());
        }else{
            jobTitle = new JobTitle();
        }
    }

    private void nameValidation(String name) {
        if(name == null){
            throw new IllegalArgumentException("구매담당자 이름을 입력해주세요.");
        }
    }

    public PurchasingManagerModel toModel() {
        return PurchasingManagerModel.builder()
                .name(name.get())
                .email(email.get())
                .jobTitle(jobTitle.get())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasingManager that = (PurchasingManager) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(jobTitle, that.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, jobTitle);
    }
}
