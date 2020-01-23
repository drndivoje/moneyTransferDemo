package com.drnd.moneytransfer.infrastructure.transaction.events;

import com.drnd.moneytransfer.model.Event;
import com.drnd.moneytransfer.model.transaction.Transaction;

public class TransactionCreatedEvent implements Event {
    private final Transaction transaction;
    private final long creationDate;

    public TransactionCreatedEvent(Transaction transaction) {
        this.transaction = transaction;
        this.creationDate = System.currentTimeMillis();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public long getCreationDate() {
        return creationDate;
    }
}
