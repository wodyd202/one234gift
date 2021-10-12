package com.one234gift.orderservice.command.application;

public interface Producer {
    void publish(String payload);
}
