package com.drnd.moneytransfer.transaction.exceptions;

public class TransactionCommitFailedException extends RuntimeException {
    private final String transactionId;

    public TransactionCommitFailedException(String transactionId, String reason) {
        super(reason);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
