package com.one234gift.happycallservice.infrastructure;

import com.one234gift.happycallservice.application.HappyCallRepository;
import com.one234gift.happycallservice.domain.HappyCall;
import com.one234gift.happycallservice.domain.read.HappyCallModel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.one234gift.happycallservice.domain.QHappyCall.happyCall;
import static com.querydsl.core.types.Projections.constructor;

@Repository
public class QuerydslHappyCallRepository implements HappyCallRepository {
    @PersistenceContext private EntityManager entityManager;
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<HappyCall> findByIdAndUserId(long seq, String userId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(happyCall)
                        .where(happyCall.userId.eq(userId).and(happyCall.seq.eq(seq)))
                        .fetchFirst()
        );
    }

    @Override
    public List<HappyCallModel> findTodayHappyCallsByUserId(String userId) {
        return jpaQueryFactory.select(constructor(HappyCallModel.class,
                        happyCall.seq,
                        happyCall.userId,
                        happyCall.orderId,
                        happyCall.customerName,
                        happyCall.customerCategory,
                        happyCall.callDate,
                        happyCall.read))
                .from(happyCall)
                .where(happyCall.userId.eq(userId).and(happyCall.callDate.eq(LocalDate.now())))
                .orderBy(happyCall.read.desc())
                .fetch();
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
