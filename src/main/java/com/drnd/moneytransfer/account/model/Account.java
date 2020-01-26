package com.drnd.moneytransfer.account.model;

import com.drnd.moneytransfer.account.exceptions.AccountTransferFailedException;

public class Account {
    private static final double INITIAL_BALANCE = 10d;
    private long accountId;
    private String firstName;
    private String lastName;
    private volatile double value;

    public Account(long accountId, String firstName, String lastName) {
        //temporary solution to generate account id
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.value = INITIAL_BALANCE;
    }


    public void withdraw(double amount) throws AccountTransferFailedException {
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

    public String getLastname() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
