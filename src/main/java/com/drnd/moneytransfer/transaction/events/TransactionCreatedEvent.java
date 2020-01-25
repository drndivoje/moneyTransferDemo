package com.drnd.moneytransfer.transaction.events;

import com.drnd.moneytransfer.messaging.Event;
import com.drnd.moneytransfer.transaction.model.Transaction;

public class TransactionCreatedEvent implements Event {
    private final long fromAccountId;
    private final long toAccountId;
    private final double amount;
    private final long creationDate;

    public TransactionCreatedEvent(Transaction transaction) {
        this.fromAccountId = transaction.getFromAccountId();
        this.toAccountId = transaction.getToAccountId();
        this.amount = transaction.getAmount();
        this.creationDate = System.currentTimeMillis();
    }


    public long getCreationDate() {
        return creationDate;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public double getAmount() {
        return amount;
    }
}
