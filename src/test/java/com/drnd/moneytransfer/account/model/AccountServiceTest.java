package com.drnd.moneytransfer.account.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void setUp() {
        this.accountService = new AccountService(new InMemoryAccountRepository());
    }

    @Test
    public void shouldTransferToAccount() {
        Account account1 = accountService.createAccount(1, "Max", "Mustermann");
        Account account2 = accountService.createAccount(2, "John", "Doe");


        accountService.transfer(account1.getId(), account2.getId(), 5);

        Account updatedAccount1 = accountService.getAccount(account1.getId());
        Account updatedAccount2 = accountService.getAccount(account2.getId());
        assertEquals(5, updatedAccount1.getAmount(), 0);
        assertEquals(15, updatedAccount2.getAmount(), 0);
    }


}