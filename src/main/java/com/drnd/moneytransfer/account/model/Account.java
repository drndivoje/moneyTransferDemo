package com.drnd.moneytransfer.account.model;

import com.drnd.moneytransfer.account.exceptions.AccountTransferFailedException;

public class Account {
    private long accountId;
    private String firstName;
    private String lastName;
    private double value;

    public Account(String firstName, String lastName) {
        //temporary solution to generate account id
        this.accountId = System.currentTimeMillis();
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void withdraw(double amount) {
        if (value < amount) {
            throw new AccountTransferFailedException(this.accountId, "Failed to withdraw amount of " + amount + " from account " + accountId);
        } else {
            value = value -amount;
        }
    }

    public Long getId() {
        return accountId;
    }


    public void addAmount(double amount) {
        value = value + amount;
    }

    public double getAmount() {
        return this.value;
    }
}
