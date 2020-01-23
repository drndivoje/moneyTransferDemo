package com.drnd.moneytransfer;

import com.drnd.moneytransfer.api.TransactionController;
import com.drnd.moneytransfer.infrastructure.InMemoryEventBus;
import com.drnd.moneytransfer.infrastructure.account.InMemoryAccountRepository;
import com.drnd.moneytransfer.infrastructure.account.TransactionListener;
import com.drnd.moneytransfer.infrastructure.transaction.events.TransactionCreatedEvent;
import com.drnd.moneytransfer.model.EventBus;
import com.drnd.moneytransfer.model.account.AccountRepository;
import com.drnd.moneytransfer.model.transaction.TransactionService;

import java.io.IOException;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) throws IOException {
        EventBus eventBus = new InMemoryEventBus();

        /*
          Transaction API
         */
        TransactionService transactionService = new TransactionService(eventBus);
        TransactionController transactionController = new TransactionController(transactionService);
        get("/transactions/:id/status", transactionController::getTransactionStatus);
        post("/transactions", transactionController::createTransaction);


        /*
          Account API
         */
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransactionListener transactionListener = new TransactionListener(accountRepository);

        eventBus.registerListener(TransactionCreatedEvent.class, transactionListener::onCreateTransaction);


    }
}

