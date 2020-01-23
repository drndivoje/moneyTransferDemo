package com.drnd.moneytransfer.infrastructure;

import com.drnd.moneytransfer.model.EventListener;

public class TestEventListener implements EventListener<TestEvent> {
    private TestEvent handledEvent;

    @Override
    public void handle(TestEvent event) {
        this.handledEvent = event;
    }

    public TestEvent getHandledEvent() {
        return handledEvent;
    }
}
