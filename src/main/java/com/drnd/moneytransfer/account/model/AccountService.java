package com.drnd.moneytransfer.account.model;

import com.drnd.moneytransfer.account.exceptions.AccountTransferFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public synchronized void transfer(long fromAccountId, long toAccountId, double amount) throws AccountTransferFailedException {
        Account fromAccount = accountRepository.findById(fromAccountId);
        if (fromAccount == null) {
            throw new AccountTransferFailedException(fromAccountId, "Account does not exist");
        }
        Account toAccount = accountRepository.findById(toAccountId);

        if (toAccount == null) {
            throw new AccountTransferFailedException(toAccountId, "Account does not exist");
        }

        fromAccount.withdraw(amount);
        toAccount.addAmount(amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        LOGGER.info("Transfer completed from account {} to account {}", fromAccountId, toAccountId);

    }

    public Account createAccount(long accountId, String firstName, String lastName) {
        Account account = new Account(accountId, firstName, lastName);
        accountRepository.save(account);
        return account;
    }

    public boolean existsAccount(long accountId) {
        return accountRepository.findById(accountId) != null;
    }

    public Account getAccount(long accountId) {
        return accountRepository.findById(accountId);
    }
}
