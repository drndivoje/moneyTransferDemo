package com.drnd.moneytransfer.infrastructure;

import com.drnd.moneytransfer.model.Event;
import com.drnd.moneytransfer.model.EventBus;
import com.drnd.moneytransfer.model.EventListener;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventBus implements EventBus {
    private final Map<String, EventListener> listeners;

    public InMemoryEventBus() {
        this.listeners = new HashMap<>();
    }

    @Override
    public void publish(Event event) {
        if (listeners.containsKey(event.getClass().getName())) {
            EventListener eventListener = listeners.get(event.getClass().getName());
            eventListener.handle(event);
        }

    }

    @Override
    public <T extends Event> void registerListener(Class<T> eventClass, EventListener<T> listener) {
        listeners.put(eventClass.getName(), listener);
    }
}
