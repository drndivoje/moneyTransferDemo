package com.drnd.moneytransfer.model.transaction;

import com.drnd.moneytransfer.infrastructure.InMemmoryEventBus;
import com.drnd.moneytransfer.infrastructure.transaction.InMemoryTransactionRepository;
import com.drnd.moneytransfer.infrastructure.transaction.TransactionCreatedEvent;
import com.drnd.moneytransfer.model.EventBus;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final EventBus eventBus;

    public TransactionService() {
        this.transactionRepository = new InMemoryTransactionRepository();
        this.eventBus = new InMemmoryEventBus();
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
