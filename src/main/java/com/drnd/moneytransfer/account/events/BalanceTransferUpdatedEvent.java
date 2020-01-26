package com.drnd.moneytransfer.account.events;

import com.drnd.moneytransfer.messaging.Event;

public class BalanceTransferUpdatedEvent implements Event {
    private final String transactionId;

    public BalanceTransferUpdatedEvent(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "BalanceTransferUpdatedEvent{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }


}
