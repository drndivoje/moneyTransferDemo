package com.drnd.moneytransfer.account.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferUpdatedEvent;
import com.drnd.moneytransfer.account.model.AccountService;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;

public class TransactionCreatedListener extends EventListener<TransactionCreatedEvent> {

    private final AccountService accountService;
    private final EventBus eventBus;

    public TransactionCreatedListener(EventBus eventBus, AccountService accountService) {
        super(TransactionCreatedEvent.class);
        this.accountService = accountService;
        this.eventBus = eventBus;
    }

    @Override
    protected void handle(TransactionCreatedEvent event) {
        long fromAccountId = event.getFromAccountId(),
                toAccountId = event.getToAccountId();
        double amount = event.getAmount();
        accountService.transfer(fromAccountId, toAccountId, amount);
        eventBus.publish(new BalanceTransferUpdatedEvent(event.getTransactionId()));
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
