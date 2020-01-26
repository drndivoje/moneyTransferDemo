package com.drnd.moneytransfer.transaction.api;

import com.drnd.moneytransfer.transaction.model.Transaction;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import java.time.Instant;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;

public class TransactionController {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        this.objectMapper = new ObjectMapper();
    }

    public Object createTransaction(Request request, Response response) throws Exception {
        TransactionJson transactionJson = objectMapper.readValue(request.body(), TransactionJson.class);
        Transaction transaction = new Transaction(transactionJson.getFromAccount(),
                transactionJson.getToAccount(),
                transactionJson.getAmount());

        Transaction savedTransaction = transactionService.createTransaction(transaction);
        return new TransactionIdJson(savedTransaction.getId());
    }

    public Object getTransaction(Request request, Response response) throws Exception {
        String id = request.params("id");
        Transaction transaction = transactionService.getTransaction(id);
        if (transaction == null) {
            response.status(404);
            return new ErrorResponseJson("Cannot find transaction " + id);
        }
        response.status(200);
        return new TransactionStatusJson(transaction.getId(),
                transaction.getStatus().toString(),
                ISO_INSTANT.format(Instant.ofEpochMilli(transaction.getLastModifiedDate())));
    }
}
