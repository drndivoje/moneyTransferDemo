package com.drnd.moneytransfer.account.api;

public class AccountIdJson {
    private final long accountId;

    public AccountIdJson(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

}
