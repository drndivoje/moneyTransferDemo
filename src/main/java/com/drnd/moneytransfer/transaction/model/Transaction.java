package com.drnd.moneytransfer.transaction.model;

import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final long fromAccountId;
    private final long toAccountId;

    private volatile TransactionStatus status;

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

    public long getFromAccountId() {
        return this.fromAccountId;
    }

    public long getToAccountId() {
        return this.toAccountId;
    }

    public double getAmount() {
        return amount;
    }


    public String getId() {
        return this.transactionId;
    }

    public TransactionStatus getStatus() {
        return this.status;
    }

    public void commit() {
        if (status.equals(TransactionStatus.PENDING)) {
            this.status = TransactionStatus.SUCCESSFUL;
            this.lastModifiedTimestamp = System.currentTimeMillis();
        }

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


    public void discard() {
        this.status = TransactionStatus.DISCARD;
    }
}
