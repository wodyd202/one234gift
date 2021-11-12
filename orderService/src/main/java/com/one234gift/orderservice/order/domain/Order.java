package com.one234gift.orderservice.order.domain;

import com.one234gift.orderservice.order.domain.exception.AlreadyCenceledException;
import com.one234gift.orderservice.order.domain.exception.AlreadyDeliveryFinishedException;
import com.one234gift.orderservice.order.domain.exception.AlreadyOrderException;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.domain.value.*;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.one234gift.orderservice.order.domain.value.OrderState.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * 주문
 */
@Entity
@Table(name = "orders")
@DynamicUpdate
public class Order {

    /**
     * 주문 고유 번호
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    /**
     * 주문 상품 정보
     */
    @Embedded
    @AttributeOverride(name = "product", column = @Column(name = "product", nullable = false, length = 20))
    private Product product;

    /**
     * 주문 고객 정보
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "customer_id",nullable = false)),
            @AttributeOverride(name = "name", column = @Column(name = "customer_name",nullable = false, length = 30)),
            @AttributeOverride(name = "category", column = @Column(name = "customer_category",nullable = false, length = 15))
    })
    private CustomerInfo customerInfo;

    /**
     * 영업자 정보
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "sales_user_name", nullable = false, length = 10)),
            @AttributeOverride(name = "phone", column = @Column(name = "sales_user_phone", nullable = false, length = 13))
    })
    private SalesUser salesUser;

    /**
     * 배송지 정보
     */
    @Embedded
    @AttributeOverride(name = "addressDetail", column = @Column(name = "delivery", nullable = false, length = 50))
    private Delivery delivery;

    /**
     * 비고
     */
    @Embedded
    @AttributeOverride(name = "content", column = @Column(name = "content", length = 100))
    private Content content;

    /**
     * 주문 수량
     */
    @Embedded
    @AttributeOverride(name = "quantity", column = @Column(name = "quantity", nullable = false))
    private OrderQuantity quantity;

    /**
     * 매입 단가
     */
    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "purchase_price", nullable = false))
    private Price purchasePrice;

    /**
     * 매출 단가
     */
    @Embedded
    @AttributeOverride(name = "price", column = @Column(name = "sale_price", nullable = false))
    private Price salePrice;

    /**
     * 주문 타입
     * #SAMPLE  샘플 발주
     * #PRODUCT 상품 발주
     */
    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderType type;

    /**
     * 주문일자
     */
    @Column(nullable = false)
    private LocalDateTime createDatetime;

    /**
     * 주문 상태
     * #REGISTER    주문 등록
     * #CENCEL      주문 취소
     * #FINISH      거래 완료
     */
    @Enumerated(STRING)
    @Column(nullable = false)
    private OrderState state;

    protected Order(){}

    @Builder
    public Order(Product product,
                 CustomerInfo customerInfo,
                 SalesUser salesUser,
                 Delivery delivery,
                 Content content,
                 OrderQuantity quantity,
                 Price purchasePrice,
                 Price salePrice,
                 OrderType type) {
        setProduct(product);
        setCustomerInfo(customerInfo);
        setSalesUser(salesUser);
        setDelivery(delivery);
        setContent(content);
        setQuantity(quantity);
        setPurchasePrice(purchasePrice);
        setSalePrice(salePrice);
        setType(type);
        this.createDatetime = LocalDateTime.now();
    }

    private void setType(OrderType type) {
        if(type == null){
            throw new IllegalArgumentException("주문 타입을 입력해주세요.");
        }
        this.type = type;
    }

    private void setSalePrice(Price salePrice) {
        if(salePrice == null){
            throw new IllegalArgumentException("판매 단가를 입력해주세요.");
        }
        this.salePrice = salePrice;
    }

    private void setPurchasePrice(Price purchasePrice) {
        if(purchasePrice == null){
            throw new IllegalArgumentException("매입 단가를 입력해주세요.");
        }
        this.purchasePrice = purchasePrice;
    }

    private void setQuantity(OrderQuantity quantity) {
        if(quantity == null){
            throw new IllegalArgumentException("수량 정보를 입력해주세요.");
        }
        this.quantity = quantity;
    }

    private void setProduct(Product product) {
        if(product == null){
            throw new IllegalArgumentException("상품명을 입력해주세요.");
        }
        this.product = product;
    }

    private void setCustomerInfo(CustomerInfo customerInfo) {
        if(customerInfo == null){
            throw new IllegalArgumentException("고객 정보를 입력해주세요.");
        }
        this.customerInfo = customerInfo;
    }

    private void setDelivery(Delivery delivery) {
        if(delivery == null){
            throw new IllegalArgumentException("배송지 정보를 입력해주세요.");
        }
        this.delivery = delivery;
    }

    private void setSalesUser(SalesUser salesUser) {
        if(salesUser == null){
            throw new IllegalArgumentException("영업자 정보를 입력해주세요.");
        }
        this.salesUser = salesUser;
    }

    private void setContent(Content content) {
        if(content == null){
            this.content = Content.getInstance();
        }else{
            this.content = content;
        }
    }

    /**
     * - 주문
     */
    public void place() {
        if(state != null){
            throw new AlreadyOrderException();
        }
        state = REGISTER;
    }

    /**
     * - 주문 취소
     * # 배송 완료 주문은 취소 불가
     */
    public void cancel() {
        verifyCencelAble();
        state = CENCEL;
    }

    /**
     * - 주문 완료
     */
    public void finish() {
        verifyFinishable();
        state = FINISH;
    }

    private void verifyCencelAble() {
        if(state.equals(FINISH)){
            throw new AlreadyDeliveryFinishedException();
        }
    }

    private void verifyFinishable() {
        if(!state.equals(REGISTER)){
            throw new AlreadyCenceledException();
        }
    }

    public OrderModel toModel() {
        return OrderModel.builder()
                .id(id)
                .product(product)
                .customerInfo(customerInfo)
                .salesUser(salesUser)
                .content(getContent())
                .delivery(delivery)
                .quantity(quantity)
                .purchasePrice(purchasePrice)
                .salePrice(salePrice)
                .type(type)
                .createDateTime(createDatetime)
                .state(state)
                .build();
    }

    private Content getContent() {
        return content == null ? Content.getInstance() : content;
    }
}
