package com.one234gift.customerservice.command.application;

public interface Producer {
    void publish(String payload);
}
