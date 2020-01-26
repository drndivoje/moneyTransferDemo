package com.drnd.moneytransfer.account.exceptions;

public class AccountTransferFailedException extends Exception {
    private long accountId;

    @Override
    public String toString() {
        return "AccountTransferFailedException{" +
                "accountId=" + accountId +
                '}';
    }

    public AccountTransferFailedException(long accountId, String cause) {
        super(cause);
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }
}
