package com.one234gift.orderservice.query.infrastructure;

import com.one234gift.orderservice.common.Pageable;
import com.one234gift.orderservice.domain.read.OrderModel;
import com.one234gift.orderservice.query.application.QueryOrderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.one234gift.orderservice.domain.QOrder.order;
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
    public List<OrderModel> findAll(String userId, Pageable pageable) {
        return jpaQueryFactory.select(constructor(OrderModel.class,
                        order.id,
                        order.product(),
                        order.customerInfo(),
                        order.quantity(),
                        order.salePrice(),
                        order.state,
                        order.createDatetime
                    ))
                .from(order)
                .where(order.salesUser().phone.eq(userId))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public long countAll(String userId) {
        return jpaQueryFactory.selectOne()
                .from(order)
                .where(order.salesUser().phone.eq(userId))
                .fetchCount();
    }
}
