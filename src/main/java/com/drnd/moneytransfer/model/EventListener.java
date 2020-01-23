package com.drnd.moneytransfer.model;

public interface EventListener<T extends Event> {
    Class<T> getEventType();

    void handle(T event);
}
