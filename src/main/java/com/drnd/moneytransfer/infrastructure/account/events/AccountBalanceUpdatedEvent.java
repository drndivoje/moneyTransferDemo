package com.drnd.moneytransfer.infrastructure.account.events;

import com.drnd.moneytransfer.model.Event;

public class AccountBalanceUpdatedEvent implements Event {
    private final String transactionId;

    public AccountBalanceUpdatedEvent(String transactionId) {
        this.transactionId = transactionId;
    }
}
