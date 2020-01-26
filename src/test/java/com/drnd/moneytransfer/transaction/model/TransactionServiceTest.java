package com.drnd.moneytransfer.transaction.model;


import com.drnd.moneytransfer.messaging.EventBus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionService(new EventBus());
    }

    @Test
    public void shouldCreateTransaction() {

        long fromId = 1234566L;
        long toId = 5325523L;
        Transaction transaction = new Transaction(fromId, toId, 30.3);

        transactionService.createTransaction(transaction);
        TransactionStatus status = transactionService.getStatus(transaction.getId());
        assertEquals("Newly created transaction should be in PENDING status", TransactionStatus.PENDING, status);
    }

    @Test
    public void shouldCommitTransaction() {
        long fromId = 1234566L;
        long toId = 5325523L;
        Transaction transaction = new Transaction(fromId, toId, 30.3);
        transactionService.createTransaction(transaction);
        transactionService.commit(transaction.getId());
        TransactionStatus status = transactionService.getStatus(transaction.getId());
        assertEquals("Committed transaction should be in SUCCESSFUL status", TransactionStatus.SUCCESSFUL, status);
    }

    @Test
    public void shouldDiscardTransaction() {
        long fromId = 1234566L;
        long toId = 5325523L;
        Transaction transaction = new Transaction(fromId, toId, 30.3);
        transactionService.createTransaction(transaction);
        transactionService.discard(transaction.getId());
        TransactionStatus status = transactionService.getStatus(transaction.getId());
        assertEquals("Discarded transaction should be in DISCARD status", TransactionStatus.DISCARD, status);
    }
}