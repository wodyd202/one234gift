package com.one234gift.saleshistoryservice.query.infrastructure;

import com.one234gift.saleshistoryservice.common.Pageable;
import com.one234gift.saleshistoryservice.domain.read.SalesHistoryModel;
import com.one234gift.saleshistoryservice.query.application.QuerySaleshistoryRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.one234gift.saleshistoryservice.domain.QSalesHistory.salesHistory;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional(readOnly = true)
public class QuerydslQuerySalesHistoryRepository implements QuerySaleshistoryRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SalesHistoryModel> findAll(long customerId, Pageable pageable) {
        return jpaQueryFactory.select(salesHistoryListModel())
                .from(salesHistory)
                .where(eqCustomerId(customerId))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public long countAll(long customerId) {
        return jpaQueryFactory.selectOne()
                .from(salesHistory)
                .where(eqCustomerId(customerId))
                .fetchCount();
    }

    @Override
    public Optional<SalesHistoryModel> findBySalesHistoryId(long salesHistoryId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(salesHistoryListModel())
                        .from(salesHistory)
                        .where(eqSalesHistoryId(salesHistoryId))
                        .fetchFirst()
        );
    }

    private BooleanExpression eqCustomerId(long customerId){
        return salesHistory.customerId.eq(customerId);
    }

    private BooleanExpression eqSalesHistoryId(long salesHistoryId){
        return salesHistory.id.eq(salesHistoryId);
    }

    private ConstructorExpression<SalesHistoryModel> salesHistoryListModel() {
        return constructor(SalesHistoryModel.class,
                salesHistory.id,
                salesHistory.content(),
                salesHistory.createDateTime,
                salesHistory.writer()
        );
    }
}
