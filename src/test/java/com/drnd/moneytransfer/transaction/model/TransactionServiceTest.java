package com.drnd.moneytransfer.transaction.model;


import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.drnd.moneytransfer.transaction.model.TransactionStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionServiceTest {

    @Test
    public void shouldCreateTransaction() {
        TransactionService transactionService = new TransactionService(new EventBus());

        long fromId = 1234566L;
        long toId = 5325523L;
        Transaction transaction = new Transaction(fromId, toId, 30.3);

        transactionService.createTransaction(transaction);
        TransactionStatus status = transactionService.getStatus(transaction.getId());
        assertEquals("Newly created transaction should be in PENDING status", TransactionStatus.PENDING, status);
    }



}