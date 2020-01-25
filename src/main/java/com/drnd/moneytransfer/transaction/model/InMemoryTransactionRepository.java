package com.drnd.moneytransfer.transaction.model;

import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionStore;

    public InMemoryTransactionRepository() {
        this.transactionStore = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Transaction transaction) {
        transactionStore.put(transaction.getId(), transaction);
    }

    @Override
    public Transaction findById(String id) {
        return transactionStore.get(id);
    }
}
