package com.drnd.moneytransfer.model.transaction;

import com.drnd.moneytransfer.api.TransactionJson;

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
}
