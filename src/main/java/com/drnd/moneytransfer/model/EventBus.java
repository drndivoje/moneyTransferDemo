package com.drnd.moneytransfer.model;

public interface EventBus {
    void publish(Event event);

    <T extends Event> void registerListener(Class<T> eventClass, EventListener<T> listener);

}
