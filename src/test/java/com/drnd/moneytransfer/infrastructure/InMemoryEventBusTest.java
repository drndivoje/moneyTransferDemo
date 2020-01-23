package com.drnd.moneytransfer.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class InMemoryEventBusTest {

    @Test
    public void shouldPublishAndListenOnEvent() {
        InMemoryEventBus eventBus = new InMemoryEventBus();
        TestEventListener testEventEventListener = new TestEventListener();
        eventBus.registerListener(TestEvent.class, testEventEventListener);
        TestEvent event = new TestEvent();

        eventBus.publish(event);

        assertEquals(event, testEventEventListener.getHandledEvent());
    }

}