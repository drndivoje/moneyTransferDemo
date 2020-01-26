package com.drnd.moneytransfer.transaction.model;

import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final EventBus eventBus;

    public TransactionService(EventBus eventBus) {
        this.transactionRepository = new InMemoryTransactionRepository();
        this.eventBus = eventBus;
    }

    public Transaction createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        eventBus.publish(new TransactionCreatedEvent(transaction));
        return transaction;
    }

    public TransactionStatus getStatus(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        return transaction.getStatus();
    }

    public Transaction getTransaction(String transactionId) {
        return transactionRepository.findById(transactionId);
    }


    public synchronized void commit(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        transaction.commit();
        transactionRepository.save(transaction);
    }


}
