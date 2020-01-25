package com.drnd.moneytransfer.account.api;

import com.drnd.moneytransfer.account.model.Account;
import com.drnd.moneytransfer.account.model.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

public class AccountController {
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.objectMapper = new ObjectMapper();

    }

    public Object createAccount(Request request, Response response) throws Exception {
        AccountJson accountJson = objectMapper.readValue(request.body(), AccountJson.class);
        Account account = accountService.createAccount(accountJson.getFirstName(), accountJson.getLastName());
        return new AccountIdJson(account.getId());
    }
}
