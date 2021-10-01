package com.one234gift.customerservice.command.infrastructure;

import com.one234gift.customerservice.command.application.CustomerHistoryRepository;
import com.one234gift.customerservice.domain.CustomerHistory;
import com.one234gift.customerservice.domain.read.CustomerHistoryModel;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.one234gift.customerservice.domain.QCustomerHistory.customerHistory;
import static com.querydsl.core.types.dsl.Expressions.asSimple;

@Repository
@Transactional
public class QuerydslCustomerHistoryRepository implements CustomerHistoryRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<CustomerHistoryModel> findById(long customerId) {
        return jpaQueryFactory.select(Projections.constructor(CustomerHistoryModel.class,
                asSimple(customerId),
                customerHistory.type,
                customerHistory.payload,
                customerHistory.manager(),
                customerHistory.createDateTime
                        ))
                .from(customerHistory)
                .where(customerHistory.customerId.eq(customerId))
                .fetch();
    }

    @Override
    public void save(CustomerHistory customerHistory) {
        entityManager.persist(customerHistory);
    }
}
