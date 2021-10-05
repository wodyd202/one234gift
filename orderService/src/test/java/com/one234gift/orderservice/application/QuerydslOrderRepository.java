package com.one234gift.orderservice.application;

import com.one234gift.orderservice.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class QuerydslOrderRepository implements OrderRepository{
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public void save(Order order) {
        if(entityManager.contains(order)){
            entityManager.merge(order);
        }else{
            entityManager.persist(order);
        }
    }
}
