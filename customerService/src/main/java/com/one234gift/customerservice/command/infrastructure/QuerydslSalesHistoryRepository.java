package com.one234gift.customerservice.command.infrastructure;

import com.one234gift.customerservice.command.application.SalesHistoryRepository;
import com.one234gift.customerservice.domain.QSalesHistory;
import com.one234gift.customerservice.domain.SalesHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.one234gift.customerservice.domain.QSalesHistory.salesHistory;


@Repository
@Transactional
public class QuerydslSalesHistoryRepository implements SalesHistoryRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public void save(SalesHistory salesHistory) {
        if(entityManager.contains(salesHistory)){
            entityManager.merge(salesHistory);
        }else{
            entityManager.persist(salesHistory);
        }
    }

    @Override
    public Optional<SalesHistory> findByIdAndUserId(long id, String userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(salesHistory)
                .where(salesHistory.id.eq(id).and(salesHistory.manager().phone.eq(userId)))
                .fetchFirst());
    }

    @Override
    public void remove(Long id) {
        jpaQueryFactory.delete(salesHistory)
                .where(salesHistory.id.eq(id))
                .execute();
    }
}
