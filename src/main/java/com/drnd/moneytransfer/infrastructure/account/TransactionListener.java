package com.drnd.moneytransfer.infrastructure.account;

import com.drnd.moneytransfer.infrastructure.transaction.TransactionCreatedEvent;
import com.drnd.moneytransfer.model.Event;
import com.drnd.moneytransfer.model.EventListener;
import com.drnd.moneytransfer.model.account.AccountRepository;

public class TransactionListener implements EventListener<TransactionCreatedEvent> {
    private final AccountRepository accountRepository;

    public TransactionListener(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Class<TransactionCreatedEvent> getEventType() {
        return TransactionCreatedEvent.class;
    }

    @Override
    public void handle(TransactionCreatedEvent event) {

    }
}
