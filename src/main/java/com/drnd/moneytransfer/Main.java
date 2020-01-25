package com.drnd.moneytransfer;

import com.drnd.moneytransfer.account.api.AccountController;
import com.drnd.moneytransfer.account.listeners.TransactionCreatedListener;
import com.drnd.moneytransfer.account.model.AccountRepository;
import com.drnd.moneytransfer.account.model.AccountService;
import com.drnd.moneytransfer.account.model.InMemoryAccountRepository;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.api.TransactionController;
import com.drnd.moneytransfer.transaction.listeners.BalanceTransferUpdatedListener;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) throws IOException {
        EventBus eventBus = new EventBus();
        ObjectMapper objectMapper = new ObjectMapper();

        /*
          Transaction API
         */
        TransactionService transactionService = new TransactionService(eventBus);
        TransactionController transactionController = new TransactionController(transactionService);

        path("/transactions", () -> {
            get("/:id", transactionController::getTransaction);
            post("", transactionController::createTransaction, objectMapper::writeValueAsString);
        });
        eventBus.register(new BalanceTransferUpdatedListener(transactionService));


        /*
          Account API
         */

        AccountRepository accountRepository = new InMemoryAccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService);
        path("/accounts", () -> {
            after("*", (request, response) -> {response.header("Content-Type", "application/json");});
            get("/:id", (request, response) -> "ssss");
            post("", accountController::createAccount, objectMapper::writeValueAsString);
        });

        eventBus.register(new TransactionCreatedListener(accountService));

    }
}

