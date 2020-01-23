package com.drnd.moneytransfer.model;

public interface EventBus<T extends Event> {
    void publish(Event event);
    void registerListener(EventListener listener);

}
