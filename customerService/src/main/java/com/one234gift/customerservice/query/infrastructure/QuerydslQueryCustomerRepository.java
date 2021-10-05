package com.one234gift.customerservice.query.infrastructure;

import com.one234gift.customerservice.domain.read.CustomerModel;
import com.one234gift.customerservice.query.application.QueryCustomerRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.one234gift.customerservice.domain.QCustomer.customer;

@Repository
public class QuerydslQueryCustomerRepository implements QueryCustomerRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Override
    public Optional<CustomerModel> findById(long customerId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(Projections.constructor(CustomerModel.class,
                                customer.id,
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
}
