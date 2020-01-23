package com.drnd.moneytransfer.model;

@FunctionalInterface
public interface EventListener<T extends Event> {
    void handle(T event);
}
