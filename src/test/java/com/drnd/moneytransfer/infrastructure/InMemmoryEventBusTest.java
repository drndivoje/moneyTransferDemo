package com.drnd.moneytransfer.infrastructure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class InMemmoryEventBusTest {

    @Test
    public void shouldPublishAndListenOnEvent() {
        InMemmoryEventBus eventBus = new InMemmoryEventBus();
        TestEventListener testEventEventListener = new TestEventListener();
        eventBus.registerListener(testEventEventListener);
        TestEvent event = new TestEvent();

        eventBus.registerListener(testEventEventListener);
        eventBus.publish(event);

        assertEquals(event, testEventEventListener.getHandledEvent());
    }

}