package com.one234gift.happycallservice.infrastructure;

import com.one234gift.happycallservice.application.HappyCallRepository;
import com.one234gift.happycallservice.common.Pageable;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.one234gift.happycallservice.domain.value.SalesUserInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.one234gift.happycallservice.domain.QHappyCall.happyCall;

@Repository
public class QuerydslHappyCallRepository implements HappyCallRepository {
    @PersistenceContext private EntityManager entityManager;
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<HappyCall> findByIdAndSalesUser(long seq, SalesUserInfo salesUser) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(happyCall)
                        .where(eqSalesUser(salesUser), eqSeq(seq))
                        .fetchFirst()
        );
    }

    @Override
    public List<HappyCallModel> findTodayHappyCall(Pageable pageable, SalesUserInfo salesUser) {
        return jpaQueryFactory.select(happyCall)
                .from(happyCall)
                .where(eqSalesUser(salesUser), eqWhen(LocalDate.now()))
                .limit(pageable.getSize())
                .offset(pageable.getSize() * pageable.getPage())
                .orderBy(happyCall.read.desc())
                .fetch().stream().map(HappyCall::toModel).collect(Collectors.toList());
    }

    @Override
    public long countTodayCallReservation(SalesUserInfo salesUser) {
        return jpaQueryFactory.selectOne()
                .from(happyCall)
                .where(eqSalesUser(salesUser), eqWhen(LocalDate.now()))
                .orderBy(happyCall.read.desc())
                .fetchCount();
    }

    private BooleanExpression eqSeq(long seq){
        return happyCall.seq.eq(seq);
    }

    private BooleanExpression eqWhen(LocalDate localDate){
        return happyCall.when.eq(localDate);
    }

    private BooleanExpression eqSalesUser(SalesUserInfo salesUserInfo){
        return happyCall.salesUser().eq(salesUserInfo);
    }

    @Override
    public void save(HappyCall happyCall) {
        if(entityManager.contains(happyCall)){
            entityManager.merge(happyCall);
        }else{
            entityManager.persist(happyCall);
        }
    }
}
