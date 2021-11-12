package com.one234gift.employeeService.employee.query.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.employeeService.employee.domain.read.EmployeeModel;
import com.one234gift.employeeService.employee.query.application.QueryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public class RedisQueryEmployeeRepository implements QueryEmployeeRepository {
    @Autowired private RedisTemplate<String, Object> redisTemplate;
    @Autowired private ObjectMapper objectMapper;
    private HashOperations hashOperations;

    @Value("${redis.employee.key}")
    private String EMPLOYEE_KEY;

    @PostConstruct
    void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(EmployeeModel employeeModel) {
        hashOperations.put(EMPLOYEE_KEY, employeeModel.getPhone(), employeeModel);
    }

    public Optional<EmployeeModel> findByPhone(String phone) {
        Object obj = hashOperations.get(EMPLOYEE_KEY, phone);
        if(obj != null){
            return Optional.of(objectMapper.convertValue(obj, EmployeeModel.class));
        }
        return Optional.empty();
    }
}
