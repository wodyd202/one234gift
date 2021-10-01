package com.one234gift.customerservice.domain;

import com.one234gift.customerservice.command.application.event.ChangedCustomerEvent;
import com.one234gift.customerservice.domain.value.Manager;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer_history")
public class CustomerHistory {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false)
    private long customerId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String payload;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "updater_name", nullable = false, length = 10)),
            @AttributeOverride(name = "phone", column = @Column(name = "updater_phone", nullable = false, length = 13))
    })
    private Manager manager;

    @Column(nullable = false)
    private LocalDateTime createDateTime;

    private CustomerHistory(ChangedCustomerEvent event) {
        type = event.getType();
        payload = event.getPayload();
        customerId = event.getCustomerId();
        manager = event.getManager();
        createDateTime = LocalDateTime.now();
    }

    public static CustomerHistory of(ChangedCustomerEvent event) {
        return new CustomerHistory(event);
    }

    @Override
    public String toString() {
        return "CustomerHistory{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", type='" + type + '\'' +
                ", payload='" + payload + '\'' +
                ", manager=" + manager +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
