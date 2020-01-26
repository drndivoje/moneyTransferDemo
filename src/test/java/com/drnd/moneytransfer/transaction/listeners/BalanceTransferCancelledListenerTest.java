package com.drnd.moneytransfer.transaction.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferCancelledEvent;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.drnd.moneytransfer.transaction.model.TransactionStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BalanceTransferCancelledListenerTest {
    @Test
    public void shouldDiscardTransaction() {
        TransactionService transactionService = new TransactionService(new EventBus());
        BalanceTransferCancelledListener listener = new BalanceTransferCancelledListener(transactionService);

        Transaction transaction = new Transaction(1L, 2L, 2.2);

        transactionService.createTransaction(transaction);
        BalanceTransferCancelledEvent event = new BalanceTransferCancelledEvent(transaction.getId());
        listener.handle(event);

        assertEquals(TransactionStatus.DISCARD, transactionService.getStatus(transaction.getId()));

    }

}