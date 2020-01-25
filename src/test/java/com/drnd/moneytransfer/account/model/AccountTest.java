package com.drnd.moneytransfer.account.model;

import com.drnd.moneytransfer.account.exceptions.AccountTransferFailedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void shouldWithdrawFromAccount() {
        Account account = new Account("Max", "Mustermann");

        account.addAmount(10);
        account.withdraw(5);

        assertEquals(5.0, account.getAmount(), 0);
    }

    @Test(expected = AccountTransferFailedException.class)
    public void shouldFailToWithdrawFromAccount() {
        Account account = new Account("Max", "Mustermann");
        account.withdraw(5);
    }

}