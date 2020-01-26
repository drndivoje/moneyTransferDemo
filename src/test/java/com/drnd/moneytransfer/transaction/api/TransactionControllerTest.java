package com.drnd.moneytransfer.transaction.api;

import com.drnd.moneytransfer.account.events.BalanceTransferUpdatedEvent;
import com.drnd.moneytransfer.messaging.EventBus;
import com.drnd.moneytransfer.transaction.listeners.BalanceTransferUpdatedListener;
import com.drnd.moneytransfer.transaction.model.TransactionService;
import com.drnd.moneytransfer.transaction.model.TransactionStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import spark.Request;
import spark.Response;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    private TransactionController controller;
    private EventBus eventBus;
    private TransactionService transactionService;
    private static final String TRANSACTION_JSON = "{\"fromAccount\": 1, \"toAccount\":2, \"amount\": 2}";


    @Before
    public void setUp() {
        this.eventBus = new EventBus();

        transactionService = new TransactionService(eventBus);

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

    @Test
    public void shouldReturnCorrectStatusOfTransaction() throws Exception {
        //register listener for BalanceTransferUpdated event
        eventBus.register(new BalanceTransferUpdatedListener(transactionService));

        Request request = Mockito.mock(Request.class);
        when(request.body()).thenReturn(TRANSACTION_JSON);
        Response response = Mockito.mock(Response.class);

        //create a transaction
        Object transactionId = controller.createTransaction(request, response);
        TransactionIdJson transactionIdJson = (TransactionIdJson) transactionId;
        //when transfer is successful
        eventBus.publish(new BalanceTransferUpdatedEvent(transactionIdJson.getTransactionId()));
        sleep(50);
        when(request.params(eq("id"))).thenReturn(transactionIdJson.getTransactionId());
        //get transaction
        Object transaction = controller.getTransaction(request, response);
        //transaction should be SUCCESSFUL
        TransactionStatusJson transactionStatusJson = (TransactionStatusJson) transaction;
        assertEquals(transactionIdJson.getTransactionId(), transactionStatusJson.getTransactionId());
        assertEquals(TransactionStatus.SUCCESSFUL.toString(), transactionStatusJson.getStatus());

    }

    @Test
    public void shouldReturn404IfTransactionDoesNotExist() throws Exception {
        Request request = Mockito.mock(Request.class);
        when(request.body()).thenReturn(TRANSACTION_JSON);
        Response response = Mockito.mock(Response.class);
        String transactionId = "a8898d96-b2e4-482f-99cd-072f582d9475";
        when(request.params(eq("id"))).thenReturn(transactionId);

        Object transaction = controller.getTransaction(request, response);

        assertEquals(ErrorResponseJson.class, transaction.getClass());
        ErrorResponseJson errorResponseJson = (ErrorResponseJson) transaction;
        assertEquals("Cannot find transaction " + transactionId, errorResponseJson.getMessage());
        verify(response, times(1)).status(404);
    }

}