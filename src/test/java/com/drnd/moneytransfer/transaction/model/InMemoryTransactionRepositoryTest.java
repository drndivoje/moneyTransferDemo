package com.drnd.moneytransfer.transaction.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InMemoryTransactionRepositoryTest {

    @Test
    public void shouldSaveTransaction() {
        Transaction transaction = new Transaction(111111, 22222, 34.23);

        InMemoryTransactionRepository repository = new InMemoryTransactionRepository();
        repository.save(transaction);

        Transaction savedTransaction = repository.findById(transaction.getId());
        assertEquals(transaction, savedTransaction);

    }

}