package com.drnd.moneytransfer.transaction.api;

public class TransactionStatusJson {
    private final String transactionId;
    private final String status;
    private final String lastModified;

    public TransactionStatusJson(String transactionId, String status, String lastModified) {
        this.transactionId = transactionId;
        this.status = status;
        this.lastModified = lastModified;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getLastModified() {
        return lastModified;
    }
}
