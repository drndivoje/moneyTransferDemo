package com.drnd.moneytransfer.transaction.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferCancelledEvent;
import com.drnd.moneytransfer.account.listeners.TransactionCreatedListener;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * It handles BalanceTransferCancelledEvent event and discard the transaction.
 */
public class BalanceTransferCancelledListener extends EventListener<BalanceTransferCancelledEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionCreatedListener.class);

    private final TransactionService transactionService;

    public BalanceTransferCancelledListener(TransactionService transactionService) {
        super(BalanceTransferCancelledEvent.class);
        this.transactionService = transactionService;
    }

    @Override
    protected void handle(BalanceTransferCancelledEvent event) {
        String transactionId = event.getTransactionId();
        transactionService.discard(transactionId);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("Failed to handle BalanceTransferCancelledEvent", throwable);
    }
}
