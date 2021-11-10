package com.one234gift.customerservice.customer.query.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.customerservice.customer.domain.read.CustomerModel;
import com.one234gift.customerservice.customer.query.application.QueryCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public class RedisQueryCustomerRepository implements QueryCustomerRepository {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.customer.key}")
    private String CUSTOMER_KEY;

    @Autowired
    private ObjectMapper objectMapper;

    private HashOperations<String, Object, Object> hashOperations;

    @Override
    public void save(CustomerModel customerModel) {
        hashOperations.put(CUSTOMER_KEY, customerModel.getId(), customerModel);
    }

    @Override
    public Optional<CustomerModel> findById(long customerId) {
        Object obj = hashOperations.get(CUSTOMER_KEY, customerId);
        if(obj != null){
            return Optional.of(objectMapper.convertValue(obj, CustomerModel.class));
        }
        return Optional.empty();
    }

    @Override
    public boolean existById(Long customerId) {
        return hashOperations.get(CUSTOMER_KEY, customerId) != null;
    }

    @PostConstruct
    void setUp(){
        hashOperations = redisTemplate.opsForHash();
    }
}
