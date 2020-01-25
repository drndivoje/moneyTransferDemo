package com.drnd.moneytransfer.account.model;

public interface AccountRepository {
    void save(Account account);

    Account findById(long Id);

}
