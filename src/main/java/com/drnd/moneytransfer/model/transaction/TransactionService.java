package com.drnd.moneytransfer.model.transaction;

import com.drnd.moneytransfer.infrastructure.InMemoryEventBus;
import com.drnd.moneytransfer.infrastructure.transaction.InMemoryTransactionRepository;
import com.drnd.moneytransfer.infrastructure.transaction.events.TransactionCreatedEvent;
import com.drnd.moneytransfer.model.EventBus;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final EventBus eventBus;

    public TransactionService(EventBus eventBus) {
        this.transactionRepository = new InMemoryTransactionRepository();
        this.eventBus = eventBus;
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        eventBus.publish(new TransactionCreatedEvent(transaction));
    }

    public TransactionStatus getStatus(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        return transaction.getStatus();
    }


}
