package com.drnd.moneytransfer.transaction.api;

import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.drnd.moneytransfer.transaction.model.TransactionStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import spark.Request;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    private TransactionController controller;
    private static final String TRANSACTION_JSON = "{\"fromAccount\": 1, \"toAccount\":2, \"amount\": 2}";


    @Before
    public void setUp() {
        TransactionService transactionService = new TransactionService(new EventBus());

        controller = new TransactionController(transactionService);
    }

    @Test
    public void shouldCreateTransaction() throws Exception {
        Request request = Mockito.mock(Request.class);
        when(request.body()).thenReturn(TRANSACTION_JSON);
        Response response = Mockito.mock(Response.class);

        Object transactionId = controller.createTransaction(request, response);
        assertEquals(TransactionIdJson.class, transactionId.getClass());
        TransactionIdJson transactionIdJson = (TransactionIdJson) transactionId;
        assertNotNull(transactionIdJson.getTransactionId());

        when(request.params(eq("id"))).thenReturn(transactionIdJson.getTransactionId());
        Object transaction = controller.getTransaction(request, response);
        assertEquals(TransactionStatusJson.class, transaction.getClass());
        TransactionStatusJson transactionStatusJson = (TransactionStatusJson) transaction;
        assertEquals(transactionIdJson.getTransactionId(), transactionStatusJson.getTransactionId());
        assertEquals(TransactionStatus.PENDING.toString(), transactionStatusJson.getStatus());


    }

}