package com.one234gift.customerservice.domain.value;

import com.one234gift.customerservice.domain.Customer;
import com.one234gift.customerservice.domain.model.ChangePurchasingManager;
import com.one234gift.customerservice.domain.read.PurchasingManagerModel;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer_purchasing_manager")
public class PurchasingManager {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long seq;

    @ManyToOne(fetch = LAZY)
    private Customer customer;

    @Embedded
    @AttributeOverride(name = "managerName", column = @Column(name = "name", length = 10))
    private ManagerName name;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", length = 50))
    private Email email;

    @Embedded
    @AttributeOverride(name = "jobTitle", column = @Column(name = "job_title", length = 10))
    private JobTitle jobTitle;

    /**
     * 고객(업체) 연락처
     * - 대표 전화번호[필수]
     * - 기타 전화번호
     * - 팩스
     */
    @Embedded
    private Contact contact;

    public PurchasingManager(ChangePurchasingManager purchasingManager, Customer customer) {
        setManagerName(purchasingManager);
        setEmail(purchasingManager);
        setJobTitle(purchasingManager);
        setContect(purchasingManager);
        this.customer = customer;
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

    private void setContect(ChangePurchasingManager purchasingManager) {
        if(purchasingManager.getContact() == null){
            throw new IllegalArgumentException("구매담당자의 연락처 정보를 입력해주세요.");
        }
        contact = new Contact(purchasingManager.getContact());
    }

    public PurchasingManagerModel toModel() {
        return PurchasingManagerModel.builder()
                .name(name.get())
                .email(email.get())
                .jobTitle(jobTitle.get())
                .contact(contact.toModel())
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

    @Override
    public String toString() {
        return "PurchasingManager{" +
                "seq=" + seq +
                ", name=" + name +
                ", email=" + email +
                ", jobTitle=" + jobTitle +
                '}';
    }
}
