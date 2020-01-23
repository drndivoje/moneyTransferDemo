package com.drnd.moneytransfer.infrastructure;

import com.drnd.moneytransfer.model.Event;
import com.drnd.moneytransfer.model.EventBus;
import com.drnd.moneytransfer.model.EventListener;

import java.util.ArrayList;
import java.util.List;

public class InMemmoryEventBus implements EventBus {
    private final List<EventListener> listeners;

    public InMemmoryEventBus() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void publish(Event event) {
        listeners.stream().filter(listener -> listener.getEventType().equals(event.getClass()))
                .findFirst().ifPresent(listener -> listener.handle(event));
    }

    @Override
    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }
}
