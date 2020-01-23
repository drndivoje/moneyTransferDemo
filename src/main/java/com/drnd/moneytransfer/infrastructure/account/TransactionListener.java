package com.drnd.moneytransfer.infrastructure.account;

import com.drnd.moneytransfer.infrastructure.transaction.events.TransactionCreatedEvent;
import com.drnd.moneytransfer.model.account.AccountRepository;

public class TransactionListener {
    private final AccountRepository accountRepository;

    public TransactionListener(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void onCreateTransaction(TransactionCreatedEvent event) {

    }
}
