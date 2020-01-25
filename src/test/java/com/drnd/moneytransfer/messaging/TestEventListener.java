package com.drnd.moneytransfer.messaging;


public class TestEventListener extends EventListener<TestEvent> {
    private TestEvent handledEvent;

    public TestEventListener() {
        super(TestEvent.class);
    }

    public TestEvent getHandledEvent() {
        return handledEvent;
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void handle(TestEvent event) {
        this.handledEvent = event;
    }
}
