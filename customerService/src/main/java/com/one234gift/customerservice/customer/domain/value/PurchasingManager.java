package com.one234gift.customerservice.customer.domain.value;

import com.one234gift.customerservice.customer.command.application.model.ChangePurchasingManager;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.read.PurchasingManagerModel;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_purchasing_manager")
public class PurchasingManager {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
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

    protected PurchasingManager(){}

    public PurchasingManager(ChangePurchasingManager purchasingManager) {
        setManagerName(purchasingManager);
        setEmail(purchasingManager);
        setJobTitle(purchasingManager);
        setContect(purchasingManager);
    }

    private void setManagerName(ChangePurchasingManager purchasingManager) {
        nameValidation(purchasingManager.getName());
        name = new ManagerName(purchasingManager.getName());
    }

    private void setEmail(ChangePurchasingManager purchasingManager) {
        if(purchasingManager.getEmail() != null){
            email = new Email(purchasingManager.getEmail());
        }else{
            email = Email.getInstance();
        }
    }

    private void setJobTitle(ChangePurchasingManager purchasingManager) {
        if(purchasingManager.getJobTitle() != null){
            jobTitle = new JobTitle(purchasingManager.getJobTitle());
        }else{
            jobTitle = JobTitle.getInstance();
        }
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
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
                .id(id)
                .name(name.get())
                .email(getEmail().get())
                .jobTitle(getJobTitle().get())
                .contact(contact.toModel())
                .build();
    }

    private Email getEmail() {
        return email == null ? Email.getInstance() : email;
    }

    private JobTitle getJobTitle() {
        return jobTitle == null ? JobTitle.getInstance() : jobTitle;
    }

    @Override
    public String toString() {
        return "PurchasingManager{" +
                "id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", jobTitle=" + jobTitle +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasingManager that = (PurchasingManager) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, jobTitle, contact);
    }

    public boolean isTarget(long removePurchasingManagerIdx) {
        return removePurchasingManagerIdx == id;
    }
}
