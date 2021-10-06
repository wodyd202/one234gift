package com.one234gift.saleshistoryservice.command.infrastructure;

import com.one234gift.saleshistoryservice.command.application.SalesHistoryRepository;
import com.one234gift.saleshistoryservice.domain.SalesHistory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.one234gift.saleshistoryservice.domain.QSalesHistory.salesHistory;

@Repository
@Transactional
public class QuerydslSalesHistoryRepository implements SalesHistoryRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Optional<SalesHistory> findByIdAndUserId(long id, String userId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(salesHistory)
                        .where(eqId(id), eqUserId(userId))
                        .fetchFirst()
        );
    }

    @Override
    public boolean existByIdAndUserId(Long id, String userId) {
        return jpaQueryFactory.selectOne()
                .from(salesHistory)
                .where(eqId(id), eqUserId(userId))
                .fetchFirst() != null;
    }

    @Override
    public void remove(Long id) {
        jpaQueryFactory.delete(salesHistory)
                .where(eqId(id))
                .execute();
    }

    private BooleanExpression eqId(long id){
        return salesHistory.id.eq(id);
    }

    private BooleanExpression eqUserId(String id){
        return salesHistory.writer().phone.eq(id);
    }

    @Override
    public void save(SalesHistory salesHistory) {
        if(entityManager.contains(salesHistory)){
            entityManager.merge(salesHistory);
        }else{
            entityManager.persist(salesHistory);
        }
    }
}
