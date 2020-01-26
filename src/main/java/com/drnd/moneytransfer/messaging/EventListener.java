package com.drnd.moneytransfer.messaging;

import com.drnd.moneytransfer.account.listeners.TransactionCreatedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

/**
 * Base class to for reacting on events. It uses jdk reactive streams Subscriber interface.
 *
 * @param <T> type of event.
 */
public abstract class EventListener<T extends Event> implements Flow.Subscriber<Event> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);


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

    @Override
    public void onComplete() {
        LOGGER.trace("Handling event has been completed");
    }
}
