package com.one234gift.customerservice.query.infrastructure;

import com.one234gift.customerservice.common.Pageable;
import com.one234gift.customerservice.domain.Customer;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.one234gift.customerservice.domain.QCustomer.customer;
import static com.one234gift.customerservice.domain.QResponsible.responsible;

@Repository
@Transactional(readOnly = true)
public class QuerydslQueryCustomerRepository implements QueryCustomerRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<CustomerModel> findMy(String manager, Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(CustomerModel.class,
                        customer.id,
                        customer.category(),
                        customer.businessInfo(),
                        customer.address(),
                        customer.saleState,
                        customer.fax(),
                        customer.createDateTime)
                )
                .from(customer)
                .join(responsible).on(customer.id.eq(responsible.customerId))
                .where(responsible.manager.eq(manager))
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public long countMy(String manager) {
        return jpaQueryFactory.selectOne()
                .from(customer)
                .join(responsible).on(customer.id.eq(responsible.customerId))
                .where(responsible.manager.eq(manager))
                .fetchCount();
    }

    @Override
    public List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(CustomerModel.class,
                                customer.id,
                                customer.category(),
                                customer.businessInfo(),
                                customer.address(),
                                customer.saleState,
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

    @Override
    public long countAll(CustomerSearchDTO customerSearchDTO) {
        return jpaQueryFactory.selectOne()
                .from(customer)
                .where(eqBusinessName(customerSearchDTO.getBusinessName()),
                        eqLocation(customerSearchDTO.getLocation()),
                        eqCategory(customerSearchDTO.getCategory()),
                        eqState(customerSearchDTO.getState()))
                .fetchCount();
    }

    @Override
    public Optional<CustomerModel> findById(long customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if(customer != null){
            return Optional.ofNullable(customer.toModel());
        }else{
            return Optional.empty();
        }
    }

    @Override
    public boolean existById(Long customerId) {
        return jpaQueryFactory.selectOne()
                .from(customer)
                .fetchFirst() != null;
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
}
