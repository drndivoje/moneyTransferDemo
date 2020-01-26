package com.drnd.moneytransfer.transaction.listeners;

import com.drnd.moneytransfer.account.events.BalanceTransferUpdatedEvent;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.drnd.moneytransfer.transaction.model.TransactionStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BalanceTransferUpdatedListenerTest {
    @Test
    public void shouldCommitTransaction() {
        TransactionService transactionService = new TransactionService(new EventBus());
        BalanceTransferUpdatedListener listener = new BalanceTransferUpdatedListener(transactionService);

        long fromId = 1234566L;
        long toId = 5325523L;
        Transaction transaction = new Transaction(fromId, toId, 30.3);
        transactionService.createTransaction(transaction);
        BalanceTransferUpdatedEvent event = new BalanceTransferUpdatedEvent(transaction.getId());
        listener.handle(event);

        assertEquals(TransactionStatus.SUCCESSFUL, transactionService.getStatus(transaction.getId()));

    }

}