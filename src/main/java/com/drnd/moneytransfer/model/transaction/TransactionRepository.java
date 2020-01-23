package com.drnd.moneytransfer.model.transaction;

public interface TransactionRepository {

    void save(Transaction transaction);

    Transaction findById(String id);
}
