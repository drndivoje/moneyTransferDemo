package com.drnd.moneytransfer.account.model;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(long fromAccountId, long toAccountId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId);
        Account toAccount = accountRepository.findById(toAccountId);

        fromAccount.withdraw(amount);
        toAccount.addAmount(amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

    }

    public Account createAccount(String firstName, String lastName) {
        Account account = new Account(firstName, lastName);
        accountRepository.save(account);
        return account;
    }
}
