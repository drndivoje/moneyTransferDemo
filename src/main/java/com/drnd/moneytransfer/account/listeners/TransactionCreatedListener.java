package com.drnd.moneytransfer.account.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferCancelledEvent;
import com.drnd.moneytransfer.account.events.BalanceTransferUpdatedEvent;
import com.drnd.moneytransfer.account.exceptions.AccountTransferFailedException;
import com.drnd.moneytransfer.account.model.AccountService;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It handles new transactions and executes transfer between accounts.
 * In case of error the BalanceTransferCancelledEvent will be published otherwise BalanceTransferUpdatedEvent will be published.
 */
public class TransactionCreatedListener extends EventListener<TransactionCreatedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionCreatedListener.class);
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
        try {
            accountService.transfer(fromAccountId, toAccountId, amount);
            eventBus.publish(new BalanceTransferUpdatedEvent(event.getTransactionId()));
        } catch (AccountTransferFailedException e) {
            LOGGER.warn("failed to execute money transfer ", e);
            eventBus.publish(new BalanceTransferCancelledEvent(event.getTransactionId()));
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
