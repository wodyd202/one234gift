package com.one234gift.orderservice.command.infrastructure;

import com.one234gift.orderservice.command.application.OrderRepository;
import com.one234gift.orderservice.domain.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.one234gift.orderservice.domain.QOrder.order;

@Repository
@Transactional
public class QuerydslOrderRepository implements OrderRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Optional<Order> findByIdAndUserId(long orderId, String userId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(order)
                        .where(eqOrderId(orderId), eqSalesUser(userId))
                        .fetchFirst()
        );
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(order)
                        .where(eqOrderId(orderId))
                        .fetchFirst()
        );
    }

    private BooleanExpression eqOrderId(long orderId){
        return order.id.eq(orderId);
    }

    private BooleanExpression eqSalesUser(String userId){
        return order.salesUser().phone.eq(userId);
    }

    @Override
    public void save(Order order) {
        if(entityManager.contains(order)){
            entityManager.merge(order);
        }else{
            entityManager.persist(order);
        }
    }
}
