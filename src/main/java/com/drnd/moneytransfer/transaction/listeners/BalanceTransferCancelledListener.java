package com.drnd.moneytransfer.transaction.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferCancelledEvent;
import com.drnd.moneytransfer.messaging.EventListener;
import com.drnd.moneytransfer.transaction.model.TransactionService;

/**
 * It handles BalanceTransferCancelledEvent event and discard the transaction.
 */
public class BalanceTransferCancelledListener extends EventListener<BalanceTransferCancelledEvent> {
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

    }
}
