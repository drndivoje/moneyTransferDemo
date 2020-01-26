package com.drnd.moneytransfer.account.listeners;

import com.drnd.moneytransfer.account.model.Account;
import com.drnd.moneytransfer.account.model.AccountService;
import com.drnd.moneytransfer.account.model.InMemoryAccountRepository;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;
import com.drnd.moneytransfer.transaction.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionCreatedListenerTest {
    private TransactionCreatedListener listener;
    private AccountService accountService;

    @Before
    public void setUp() {
        accountService = new AccountService(new InMemoryAccountRepository());
        listener = new TransactionCreatedListener(new EventBus(), accountService);
    }

    @Test
    public void shouldHandleTransactionCreatedEvent() {
        Account account1 = accountService.createAccount(1, "Max", "Mustermann");
        Account account2 = accountService.createAccount(2, "John", "Doe");

        TransactionCreatedEvent transactionCreatedEvent = new TransactionCreatedEvent(
                new Transaction(1, 2, 5)
        );
        listener.handle(transactionCreatedEvent);

        Account updatedAccount1 = accountService.getAccount(account1.getId());
        Account updatedAccount2 = accountService.getAccount(account2.getId());
        assertEquals(5, updatedAccount1.getAmount(), 0);
        assertEquals(15, updatedAccount2.getAmount(), 0);
    }

}