package com.drnd.moneytransfer.messaging;

import java.util.concurrent.Flow;

/**
 * Base class to for reacting on events. It uses jdk reactive streams Subscriber interface.
 *
 * @param <T> type of event.
 */
public abstract class EventListener<T extends Event> implements Flow.Subscriber<Event> {
    protected abstract void handle(T event);

    private Class<T> eventClass;
    private Flow.Subscription subscription;

    public EventListener(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    @Override
    public final void onNext(Event item) {
        if (item.getClass().equals(eventClass)) {
            handle(eventClass.cast(item));
        }
    }

    @Override
    public final void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //Long.MAX_VALUE is considered as unbounded demand for the subscription
        subscription.request(Long.MAX_VALUE);
    }
}
