package com.drnd.moneytransfer.account.api;

import com.drnd.moneytransfer.account.model.AccountService;
import com.drnd.moneytransfer.account.model.InMemoryAccountRepository;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    private static final String CREATE_ACCOUNT_JSON = "{\n" +
            "  \"accountId\": 1,\n" +
            "  \"firstName\": \"Max\",\n" +
            "  \"lastName\": \"Musterman\"\n" +
            "}";


    private AccountController accountController;

    @Before
    public void setUp() {
        AccountService accountService = new AccountService(new InMemoryAccountRepository());
        accountController = new AccountController(accountService);
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        Request request = mock(Request.class);
        when(request.body()).thenReturn(CREATE_ACCOUNT_JSON);
        Response response = mock(Response.class);

        Object accountResponse = accountController.createAccount(request, response);

        assertEquals(AccountIdJson.class, accountResponse.getClass());
        AccountIdJson accountIdJson = (AccountIdJson) accountResponse;
        assertEquals(1, accountIdJson.getAccountId());

    }


    @Test
    public void shouldFailToCreateTwiceSameAccount() throws Exception {
        Request request = mock(Request.class);
        when(request.body()).thenReturn(CREATE_ACCOUNT_JSON);
        Response response = mock(Response.class);
        Object accountResponse = accountController.createAccount(request, response);

        Object failedResponse = accountController.createAccount(request, response);

        assertEquals(ErrorResponse.class, failedResponse.getClass());
        ErrorResponse errorResponse = (ErrorResponse) failedResponse;
        assertEquals("Account with id 1 has been already created.", errorResponse.getMessage());
        verify(response, times(1)).status(400);

    }

    @Test
    public void shouldReturnAccount() throws Exception {
        Request request = mock(Request.class);
        when(request.body()).thenReturn(CREATE_ACCOUNT_JSON);
        Response response = mock(Response.class);
        Object accountResponse = accountController.createAccount(request, response);
        when(request.params("id")).thenReturn("1");

        Object account = accountController.getAccount(request, response);

        assertEquals(account.getClass(), AccountJson.class);
        AccountJson accountJson = (AccountJson) account;
        assertEquals(1, accountJson.getAccountId());
        assertEquals("Max", accountJson.getFirstName());
        assertEquals("Musterman", accountJson.getLastName());

    }

    @Test
    public void shouldReturn404IfAccountDoesNotExits() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        when(request.params("id")).thenReturn("1");

        Object failedResponse = accountController.getAccount(request, response);

        assertEquals(ErrorResponse.class, failedResponse.getClass());
        ErrorResponse errorResponse = (ErrorResponse) failedResponse;
        assertEquals("Account with id 1 does not exist.", errorResponse.getMessage());
        verify(response, times(1)).status(404);
    }

}