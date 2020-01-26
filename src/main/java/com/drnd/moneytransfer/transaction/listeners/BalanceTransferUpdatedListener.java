package com.drnd.moneytransfer.transaction.listeners;


import com.drnd.moneytransfer.account.events.BalanceTransferUpdatedEvent;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalanceTransferUpdatedListener extends EventListener<BalanceTransferUpdatedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceTransferUpdatedListener.class);
    private final TransactionService transactionService;

    public BalanceTransferUpdatedListener(TransactionService transactionService) {
        super(BalanceTransferUpdatedEvent.class);
        this.transactionService = transactionService;
    }

    @Override
    public void handle(BalanceTransferUpdatedEvent event) {
        transactionService.commit(event.getTransactionId());
    }

    @Override
    public void onError(Throwable throwable) {
    }
}
