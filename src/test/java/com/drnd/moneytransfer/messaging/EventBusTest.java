package com.drnd.moneytransfer.messaging;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public class EventBusTest {

    @Test
    public void shouldPublishAndListenOnEvent() {
        EventBus eventBus = new EventBus();
        TestEventListener testEventEventListener = new TestEventListener();
        eventBus.register(testEventEventListener);

        TestEvent event = new TestEvent();

        eventBus.publish(event);

        await().atMost(1, SECONDS).until(() -> event.equals(testEventEventListener.getHandledEvent()));
    }

}