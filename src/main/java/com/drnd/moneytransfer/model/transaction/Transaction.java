package com.drnd.moneytransfer.model.transaction;

import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final long fromAccountId;
    private final long toAccountId;

    private TransactionStatus status;

    private final long creationTimestamp;
    private long lastModifiedTimestamp;

    private final double amount;


    public Transaction(long fromAccountId, long toAccountId, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.status = TransactionStatus.PENDING;
        this.creationTimestamp = System.currentTimeMillis();
        this.lastModifiedTimestamp = this.creationTimestamp;
    }


    public String getId() {
        return this.transactionId;
    }

    public TransactionStatus getStatus() {
        return this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return fromAccountId == that.fromAccountId &&
                toAccountId == that.toAccountId &&
                creationTimestamp == that.creationTimestamp &&
                lastModifiedTimestamp == that.lastModifiedTimestamp &&
                Double.compare(that.amount, amount) == 0 &&
                Objects.equals(transactionId, that.transactionId) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, fromAccountId, toAccountId, status, creationTimestamp, lastModifiedTimestamp, amount);
    }
}
