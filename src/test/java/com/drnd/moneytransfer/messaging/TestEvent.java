package com.drnd.moneytransfer.messaging;

import com.drnd.moneytransfer.messaging.Event;

import java.util.Objects;
import java.util.UUID;

public class TestEvent implements Event {
    private final UUID id;

    public TestEvent() {
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEvent event = (TestEvent) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
