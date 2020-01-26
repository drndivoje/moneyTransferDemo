package com.drnd.moneytransfer.account.events;

import com.drnd.moneytransfer.messaging.Event;

public class BalanceTransferCancelledEvent implements Event {
    private final String transactionId;

    public BalanceTransferCancelledEvent(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "BalanceTransferCancelledEvent{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }
}
