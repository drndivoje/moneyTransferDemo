package com.drnd.moneytransfer.account.listeners;

import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.account.model.AccountService;

public class TransactionCreatedListener extends EventListener<TransactionCreatedEvent> {

    private final AccountService accountService;

    public TransactionCreatedListener(AccountService accountService) {
        super(TransactionCreatedEvent.class);
        this.accountService = accountService;
    }

    @Override
    protected void handle(TransactionCreatedEvent event) {
        long fromAccountId = event.getFromAccountId(),
                toAccountId = event.getToAccountId();
        double amount = event.getAmount();
        accountService.transfer(fromAccountId, toAccountId, amount);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
