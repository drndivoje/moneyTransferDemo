package com.drnd.moneytransfer.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SubmissionPublisher;

public class EventBus {
    private final Map<String, EventListener> listeners;
    private SubmissionPublisher<Event> publisher = new SubmissionPublisher<>();

    public EventBus() {
        this.listeners = new HashMap<>();
    }

    public void publish(Event event) {
        publisher.submit(event);
    }

    public void register(EventListener<? extends Event> subscriber) {
        publisher.subscribe(subscriber);
    }
}
