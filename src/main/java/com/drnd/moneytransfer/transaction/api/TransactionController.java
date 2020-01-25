package com.drnd.moneytransfer.transaction.api;

import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionService;
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

        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return new TransactionIdJson(savedTransaction.getId());
    }

    public Object getTransaction(Request request, Response response) throws Exception {
        String id = request.params("id");
        Transaction transaction = transactionService.getTransaction(id);
        if (transaction == null) {
            response.status(404);
            return "Cannot find transaction";
        }
        return "OK";

    }

}
