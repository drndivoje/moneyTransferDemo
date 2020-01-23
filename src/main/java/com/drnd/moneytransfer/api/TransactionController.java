package com.drnd.moneytransfer.api;

import com.drnd.moneytransfer.model.transaction.Transaction;
import com.drnd.moneytransfer.model.transaction.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

public class TransactionController {

    private final TransactionService transactionService;
    private final ObjectMapper objectMaper;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        this.objectMaper = new ObjectMapper();
    }

    public Object createTransaction(Request request, Response response) throws Exception {
        TransactionJson transactionJson = objectMaper.convertValue(request.body(), TransactionJson.class);
        Transaction transaction = new Transaction(transactionJson.getFromAccount(),
                transactionJson.getToAccount(),
                transactionJson.getAmmount());

        transactionService.createTransaction(transaction);

        return null;
    }

    public Object getTransactionStatus(Request request, Response response) throws Exception {
        return null;
    }

}
