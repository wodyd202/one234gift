package com.one234gift.saleshistoryservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one234gift.saleshistoryservice.command.StubCustomerRepository;
import com.one234gift.saleshistoryservice.command.StubUserRepository;
import com.one234gift.saleshistoryservice.command.application.RegisterSalesHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class APITest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected RegisterSalesHistoryService registerSalesHistoryService;

    @BeforeEach
    void setUp() {
        registerSalesHistoryService.setCustomerRepository(new StubCustomerRepository());
        registerSalesHistoryService.setUserRepository(new StubUserRepository());
    }

    protected String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}