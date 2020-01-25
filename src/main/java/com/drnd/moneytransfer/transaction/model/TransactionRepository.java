package com.drnd.moneytransfer.transaction.model;

public interface TransactionRepository {

    void save(Transaction transaction);

    Transaction findById(String id);
}
