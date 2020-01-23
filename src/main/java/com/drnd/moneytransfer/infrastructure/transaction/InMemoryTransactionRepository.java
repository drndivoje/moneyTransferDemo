package com.drnd.moneytransfer.infrastructure.transaction;

import com.drnd.moneytransfer.model.EventBus;
import com.drnd.moneytransfer.model.transaction.Transaction;
import com.drnd.moneytransfer.model.transaction.TransactionRepository;

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
