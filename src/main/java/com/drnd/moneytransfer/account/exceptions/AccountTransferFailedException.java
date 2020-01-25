package com.drnd.moneytransfer.account.exceptions;

public class AccountTransferFailedException extends RuntimeException {
    private long accountId;

    public AccountTransferFailedException(long accountId, String cause) {
        super(cause);
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }
}
