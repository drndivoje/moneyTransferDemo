package com.drnd.moneytransfer.transaction.model;

import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.events.TransactionCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final EventBus eventBus;

    public TransactionService(EventBus eventBus) {
        this.transactionRepository = new InMemoryTransactionRepository();
        this.eventBus = eventBus;
    }

    public Transaction createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        eventBus.publish(new TransactionCreatedEvent(transaction));
        LOGGER.info("Transaction {} has been created ", transaction.getId());
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
        if (transaction == null) {
            LOGGER.warn("Skip to commit transaction {}. Transaction does not exist.", transactionId);
            return;
        }
        transaction.commit();
        transactionRepository.save(transaction);
        LOGGER.info("Transaction {} has been committed ", transaction.getId());
    }


    public synchronized void discard(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        if (transaction == null) {
            LOGGER.warn("Skip to discard transaction {}. Transaction does not exist.", transactionId);
            return;
        }
        transaction.discard();
        transactionRepository.save(transaction);
        LOGGER.info("Transaction {} has been discarded ", transaction.getId());
    }
}
