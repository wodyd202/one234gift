package com.one234gift.orderservice.order.query.infrastructure;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.order.domain.read.OrderModel;
import com.one234gift.orderservice.order.query.application.QueryOrderRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.one234gift.orderservice.order.domain.QOrder.order;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional(readOnly = true)
public class QuerydslQueryOrderRepository implements QueryOrderRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<OrderModel> findById(long orderId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(constructor(OrderModel.class,
                                order.id,
                                order.product(),
                                order.customerInfo(),
                                order.salesUser(),
                                order.content(),
                                order.delivery(),
                                order.quantity(),
                                order.purchasePrice(),
                                order.salePrice(),
                                order.type,
                                order.createDatetime,
                                order.state
                            ))
                        .from(order)
                        .where(order.id.eq(orderId))
                        .fetchFirst()
        );
    }

    @Override
    public List<OrderModel> findByUserId(String userId, Pageable pageable) {
        return jpaQueryFactory.select(orderListModel())
                .from(order)
                .where(eqUserId(userId))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public List<OrderModel> findAllByCustomerId(long customerId, Pageable pageable) {
        return jpaQueryFactory.select(orderListModel())
                .from(order)
                .where(eqCustomerId(customerId))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    private ConstructorExpression<OrderModel> orderListModel() {
        return constructor(OrderModel.class,
                order.id,
                order.product(),
                order.customerInfo(),
                order.quantity(),
                order.salePrice(),
                order.state,
                order.createDatetime
        );
    }

    @Override
    public long countByUserId(String userId) {
        return jpaQueryFactory.selectOne()
                .from(order)
                .where(eqUserId(userId))
                .fetchCount();
    }

    @Override
    public long countByCustomerId(long customerId) {
        return jpaQueryFactory.selectOne()
                .from(order)
                .where(eqCustomerId(customerId))
                .fetchCount();
    }

    private BooleanExpression eqUserId(String userId){
        return order.salesUser().phone.eq(userId);
    }

    private BooleanExpression eqCustomerId(long customerId){
        return order.customerInfo().id.eq(customerId);
    }
}
