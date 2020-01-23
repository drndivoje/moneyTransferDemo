package com.drnd.moneytransfer;

import com.drnd.moneytransfer.api.TransactionController;
import com.drnd.moneytransfer.model.transaction.TransactionService;

import java.io.IOException;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) throws IOException {
        TransactionService transactionService = new TransactionService();
        TransactionController transactionController = new TransactionController(transactionService);

        /*
          Transaction API
         */
        get("/transactions/:id/status", transactionController::getTransactionStatus);
        post("/transactions", transactionController::createTransaction);


    }
}

