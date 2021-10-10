package com.one234gift.customerservice.query.infrastructure;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.domain.value.SaleState;
import com.one234gift.customerservice.query.application.QueryCustomerRepository;
import com.one234gift.customerservice.query.model.CustomerSearchDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.one234gift.customerservice.domain.QCustomer.customer;
import static com.querydsl.core.types.dsl.Expressions.asSimple;

@Repository
@Transactional(readOnly = true)
public class QuerydslQueryCustomerRepository implements QueryCustomerRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(CustomerModel.class,
                        customer.id,
                        customer.category(),
                        customer.businessInfo(),
                        customer.address(),
                        customer.saleState,
                        customer.manager(),
                        customer.fax(),
                        customer.createDateTime)
                )
                .from(customer)
                .where(eqBusinessName(customerSearchDTO.getBusinessName()),
                        eqLocation(customerSearchDTO.getLocation()),
                        eqCategory(customerSearchDTO.getCategory()),
                        eqState(customerSearchDTO.getState()))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    private BooleanExpression eqBusinessName(String businessName) {
        if(businessName != null){
            return customer.businessInfo().name().name.contains(businessName);
        }
        return null;
    }

    private BooleanExpression eqLocation(String location){
        if(location != null){
            return customer.address().location().location.eq(location);
        }
        return null;
    }

    private BooleanExpression eqCategory(String category){
        if(category != null){
            return customer.category().category.eq(category);
        }
        return null;
    }

    private BooleanExpression eqState(SaleState state){
        if(state != null){
            return customer.saleState.eq(state);
        }
        return null;
    }

    @Override
    public Optional<CustomerModel> findById(long customerId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(Projections.constructor(CustomerModel.class,
                                asSimple(customerId),
                                customer.category(),
                                customer.businessInfo(),
                                customer.address(),
                                customer.saleState,
                                customer.manager(),
                                customer.fax(),
                                customer.createDateTime))
                        .from(customer)
                        .where(customer.id.eq(customerId))
                        .fetchFirst()
        );
    }

    @Override
    public boolean existById(Long customerId) {
        return jpaQueryFactory.selectOne()
                .from(customer)
                .fetchFirst() != null;
    }
}
