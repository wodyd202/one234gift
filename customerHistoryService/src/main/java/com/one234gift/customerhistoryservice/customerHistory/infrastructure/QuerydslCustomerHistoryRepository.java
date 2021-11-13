package com.one234gift.customerhistoryservice.infrastructure;

import com.one234gift.customerhistoryservice.application.CustomerHistoryRepository;
import com.one234gift.customerhistoryservice.application.model.Pageable;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.one234gift.customerhistoryservice.domain.QCustomerHistory.customerHistory;
import static com.querydsl.core.types.dsl.Expressions.asSimple;

@Transactional
@Repository
public class QuerydslCustomerHistoryRepository implements CustomerHistoryRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerHistoryModel> findByCustomerId(long customerId, Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(CustomerHistoryModel.class,
                        asSimple(customerId),
                        customerHistory.who,
                        customerHistory.payload,
                        customerHistory.type,
                        customerHistory.createDateTime))
                .from(customerHistory)
                .where(customerHistory.customerId.eq(customerId))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public long countByCustomerId(long customerId) {
        return jpaQueryFactory.selectOne()
                .from(customerHistory)
                .where(customerHistory.customerId.eq(customerId))
                .fetchCount();
    }

    @Override
    public void save(CustomerHistory customerHistory) {
        entityManager.persist(customerHistory);
    }
}
