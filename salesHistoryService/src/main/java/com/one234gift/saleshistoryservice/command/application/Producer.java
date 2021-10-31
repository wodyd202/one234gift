package com.one234gift.saleshistoryservice.command.application;

public interface Producer {
    void publish(String payload);
}
