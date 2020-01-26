package com.drnd.moneytransfer.transaction.api;

public class TransactionStatusJson {
    private final String transactionId;
    private final String status;

    public TransactionStatusJson(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
