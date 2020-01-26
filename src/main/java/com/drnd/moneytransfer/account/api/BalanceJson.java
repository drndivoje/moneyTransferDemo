package com.drnd.moneytransfer.account.api;

public class BalanceJson {

    private final long accountId;
    private final double amount;

    public BalanceJson(long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public long getAccountId() {
        return accountId;
    }
}
