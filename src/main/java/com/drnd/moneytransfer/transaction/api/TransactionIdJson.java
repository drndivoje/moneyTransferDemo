package com.drnd.moneytransfer.transaction.api;

public class TransactionIdJson {
    private final String transactionId;

    public TransactionIdJson(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
