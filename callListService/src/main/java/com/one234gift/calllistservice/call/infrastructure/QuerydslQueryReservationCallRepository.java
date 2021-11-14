package com.one234gift.calllistservice.call.infrastructure;

import com.one234gift.calllistservice.call.application.QueryReservationCallRepository;
import com.one234gift.calllistservice.call.application.model.Pageable;
import com.one234gift.calllistservice.call.application.model.ReservationCallSearchDTO;
import com.one234gift.calllistservice.call.domain.read.ReservationCallModel;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.one234gift.calllistservice.call.domain.QReservationCall.reservationCall;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional(readOnly = true)
public class QuerydslQueryReservationCallRepository implements QueryReservationCallRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReservationCallModel> findAll(ReservationCallSearchDTO reservationCallSearchDTO, Pageable pageable) {
        return jpaQueryFactory.select(constructor(ReservationCallModel.class,
                        reservationCall.salesHistoryId(),
                        reservationCall.customer(),
                        reservationCall.reserver(),
                        reservationCall.when,
                        reservationCall.read))
                .from(reservationCall)
                .where(eqFuture(reservationCallSearchDTO.getFuture()), eqRead(reservationCallSearchDTO.getRead()))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    public long countAll(ReservationCallSearchDTO reservationCallSearchDTO) {
        return jpaQueryFactory.selectOne()
                .from(reservationCall)
                .where(eqFuture(reservationCallSearchDTO.getFuture()), eqRead(reservationCallSearchDTO.getRead()))
                .fetchCount();
    }

    private BooleanExpression eqRead(Boolean read) {
        if(read == null){
            return null;
        }
        return reservationCall.read.eq(read);
    }

    private BooleanExpression eqFuture(Boolean future) {
        if(future == null){
            return null;
        }
        return reservationCall.when.gt(LocalDate.now());
    }
}
