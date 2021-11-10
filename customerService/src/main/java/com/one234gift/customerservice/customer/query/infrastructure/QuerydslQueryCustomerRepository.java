package com.one234gift.customerservice.customer.query.infrastructure;

import com.one234gift.customerservice.customer.query.application.model.Pageable;
import com.one234gift.customerservice.customer.domain.Customer;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.domain.read.ResponsibleModel;
import com.one234gift.customerservice.customer.domain.value.SaleState;
import com.one234gift.customerservice.customer.query.application.QueryCustomerListRepository;
import com.one234gift.customerservice.customer.query.application.model.CustomerSearchDTO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.one234gift.customerservice.customer.domain.QCustomer.customer;
import static com.one234gift.customerservice.customer.domain.value.QPurchasingManager.purchasingManager;
import static com.one234gift.customerservice.customer.domain.value.QResponsible.responsible;
import static com.querydsl.core.types.Projections.constructor;

@Repository
@Transactional(readOnly = true)
public class QuerydslQueryCustomerRepository implements QueryCustomerListRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CustomerModel> findByResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager, Pageable pageable) {
        return jpaQueryFactory.select(customerListModels())
                .from(customer)
                .join(responsible).on(customer.id.eq(responsible.customerId))
                .where(eqManager(manager),
                        eqBusinessName(customerSearchDTO.getBusinessName()),
                        eqLocation(customerSearchDTO.getLocation()),
                        eqCategory(customerSearchDTO.getCategory()),
                        eqState(customerSearchDTO.getState())
                )
                .limit(pageable.getSize())
                .offset(pageable.getPage() * pageable.getSize())
                .fetch();
    }

    @Override
    public long countByResponsibleUser(CustomerSearchDTO customerSearchDTO, String manager) {
        return jpaQueryFactory.selectOne()
                .from(customer)
                .join(responsible).on(customer.id.eq(responsible.customerId))
                .where(eqManager(manager),
                        eqBusinessName(customerSearchDTO.getBusinessName()),
                        eqLocation(customerSearchDTO.getLocation()),
                        eqCategory(customerSearchDTO.getCategory()),
                        eqState(customerSearchDTO.getState())
                )
                .fetchCount();
    }

    @Override
    public List<CustomerModel> findAll(CustomerSearchDTO customerSearchDTO, Pageable pageable) {
        return jpaQueryFactory.select(customerListModels()
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
        List<Customer> fetch = jpaQueryFactory.select(customer)
                .from(customer)
                .leftJoin(customer.purchasingManagers().purchasingManagers, purchasingManager)
                .fetchJoin()
                .where(eqId(customerId))
                .fetch();
        if(fetch.size() != 0){
            return Optional.of(fetch.get(0).toModel());
        }
        return Optional.empty();
    }

    @Override
    public List<ResponsibleModel> findResponsibleUsers(Long customerId) {
        return jpaQueryFactory.select(Projections.constructor(ResponsibleModel.class,
                        responsible.manager))
                .from(responsible)
                .where(responsible.customerId.eq(customerId))
                .fetch();
    }

    private BooleanExpression eqId(long customerId){
        return customer.id.eq(customerId);
    }

    private BooleanExpression eqManager(String manager){
        if(manager != null){
            return responsible.manager.eq(manager);
        }
        return null;
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

    private ConstructorExpression<CustomerModel> customerListModels() {
        return constructor(CustomerModel.class,
                customer.id,
                customer.category(),
                customer.businessInfo().name(),
                customer.address().location(),
                customer.saleState);
    }
}
