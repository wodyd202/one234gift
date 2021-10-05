package com.one234gift.orderservice.domain;

import com.one234gift.orderservice.domain.model.RegisterOrder;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.domain.value.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.one234gift.orderservice.domain.value.OrderState.WAITING;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Embedded
    @AttributeOverride(name = "product", column = @Column(name = "product", nullable = false, length = 20))
    private Product product;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "customer_id",nullable = false)),
            @AttributeOverride(name = "name", column = @Column(name = "customer_name",nullable = false, length = 30)),
            @AttributeOverride(name = "category", column = @Column(name = "customer_category",nullable = false, length = 15))
    })
    private CustomerInfo customerInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "sales_user_name", nullable = false, length = 10)),
            @AttributeOverride(name = "phone", column = @Column(name = "sales_user_phone", nullable = false, length = 13))
    })
    private SalesUser salesUser;

    @Embedded
    @AttributeOverride(name = "addressDetail", column = @Column(name = "delivery", nullable = false, length = 50))
    private Delivery delivery;

    @Embedded
    @AttributeOverride(name = "content", column = @Column(name = "content", length = 100))
    private Content content;

    @Embedded
    @AttributeOverride(name = "quantity", column = @Column(name = "quantity", nullable = false))
    private OrderQuantity quantity;

    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "purchase_price", nullable = false))
    private Price purchasePrice;

    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "sale_price", nullable = false))
    private Price salePrice;

    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderType type;

    @Column(nullable = false)
    private LocalDateTime createDatetime;

    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderState state;

    protected Order(){}

    private Order(CustomerInfo customerInfo, SalesUser salesUser, RegisterOrder registerOrder) {
        this.product = new Product(registerOrder.getProduct());
        this.customerInfo = customerInfo;
        this.salesUser = salesUser;
        this.delivery = new Delivery(registerOrder.getDelivery().getAddressDetail());
        setContent(registerOrder.getContent());
        this.quantity = new OrderQuantity(registerOrder.getQuantity().getQuantity());
        this.purchasePrice = new Price(registerOrder.getPurchasePrice());
        this.salePrice = new Price(registerOrder.getSalePrice());
        this.type = registerOrder.getType();
        createDatetime = LocalDateTime.now();
    }

    private void setContent(String content) {
        if(content == null){
            this.content = Content.getInstance();
        }else{
            this.content = new Content(content);
        }
    }

    public static Order register(CustomerInfo customerInfo, SalesUser salesUser, RegisterOrder registerOrder) {
        return new Order(customerInfo, salesUser, registerOrder);
    }

    /**
     * 주문
     */
    public void place() {
        state = WAITING;
    }

    public OrderModel toModel() {
        return OrderModel.builder()
                .id(id)
                .product(product.get())
                .customerInfo(customerInfo.toModel())
                .salesUser(salesUser.toModel())
                .content(content.get())
                .delivery(delivery.get())
                .quantity(quantity.get())
                .purchasePrice(purchasePrice.get())
                .salePrice(salePrice.get())
                .type(type)
                .createDateTime(createDatetime)
                .state(state)
                .build();
    }
}
