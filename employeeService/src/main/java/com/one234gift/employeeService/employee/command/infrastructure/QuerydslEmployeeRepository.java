package com.one234gift.employeeService.employee.command.infrastructure;

import com.one234gift.employeeService.employee.domain.Employee;
import com.one234gift.employeeService.employee.command.application.EmployeeRepository;
import com.one234gift.employeeService.employee.domain.value.Phone;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.one234gift.employeeService.employee.domain.QUser.user;

@Repository
@Transactional
public class QuerydslEmployeeRepository implements EmployeeRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Autowired private JPAEmployeeRepository jpaUserRepository;

    @Override
    public boolean existsByPhone(Phone phone) {
        return jpaQueryFactory.selectOne()
            .from(user)
            .where(eqPhone(phone))
            .fetchFirst() != null;
    }

    @Override
    public Optional<Employee> findByPhone(Phone phone) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(user)
                        .where(eqPhone(phone))
                        .fetchFirst()
        );
    }

    private BooleanExpression eqPhone(Phone phone){
        return user.phone().eq(phone);
    }

    @Override
    public void save(Employee employee) {
        jpaUserRepository.save(employee);
    }
}
