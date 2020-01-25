package com.drnd.moneytransfer.account.model;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<Long, Account> store;

    public InMemoryAccountRepository() {
        this.store = new HashMap<>();
    }

    @Override
    public void save(Account account) {
        store.put(account.getId(), account);
    }

    @Override
    public Account findById(long id) {
        return store.get(id);
    }
}
