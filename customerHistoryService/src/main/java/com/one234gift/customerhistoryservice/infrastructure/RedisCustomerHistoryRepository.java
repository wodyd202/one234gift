package com.one234gift.customerhistoryservice.infrastructure;

import com.one234gift.customerhistoryservice.application.CustomerHistoryRepository;
import com.one234gift.customerhistoryservice.common.Pageable;
import com.one234gift.customerhistoryservice.domain.CustomerHistory;
import com.one234gift.customerhistoryservice.domain.read.CustomerHistoryModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class RedisCustomerHistoryRepository implements CustomerHistoryRepository {
    @Autowired private RedisTemplate<String, CustomerHistory> redisTemplate;
    private ListOperations<String, CustomerHistory> listOperations;

    @Value("${redis.customer-history.key}")
    private String CUSTOMER_HISTORY_KEY;

    @Override
    public void save(CustomerHistory customerHistory) {
        listOperations.leftPush(customerHistoryKey(customerHistory.getCustomerId()), customerHistory);
        log.info("save customer history into redis store");
    }

    @Override
    public List<CustomerHistoryModel> findByCustomerId(String customerId, Pageable pageable) {
        return listOperations.range(customerHistoryKey(customerId), pageable.getPage() * pageable.getSize() , pageable.getSize()).stream()
                .map(CustomerHistory::toModel).collect(Collectors.toList());
    }

    @Override
    public long countByCustomerId(String customerId) {
        return listOperations.size(customerHistoryKey(customerId));
    }

    private String customerHistoryKey(String customerId) {
        return CUSTOMER_HISTORY_KEY + ":" + customerId;
    }

    @PostConstruct
    void setUp(){
        listOperations = redisTemplate.opsForList();
    }
}
