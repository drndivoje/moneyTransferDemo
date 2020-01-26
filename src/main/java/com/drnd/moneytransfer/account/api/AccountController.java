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
        long accountId = accountJson.getAccountId();
        if (accountService.existsAccount(accountId)) {
            response.status(400);
            return new ErrorResponse("Account with id " + accountId + " has been already created.");
        }

        Account account = accountService.createAccount(accountId, accountJson.getFirstName(), accountJson.getLastName());
        response.status(204);
        return new AccountIdJson(account.getId());
    }

    public Object getAccount(Request request, Response response) {
        String id = request.params("id");
        long accountId = Long.parseLong(id);
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            response.status(404);
            return new ErrorResponse("Account with id " + accountId + " does not exist.");
        }
        return toAccountJson(account);
    }

 /*   public Object updateBalance(Request request, Response response) throws Exception {
        BalanceJson balanceJson = objectMapper.readValue(request.body(), BalanceJson.class);
        String id = request.params("id");
        long accountId = Long.parseLong(id);

        if (accountService.existsAccount(accountId)) {
            accountService.transfer(accountId, balanceJson.getAmount());
            BalanceJson responseJson = new BalanceJson();
            responseJson.setAmount(balanceJson.getAmount());
            return responseJson;
        }
        response.status(400);
        return new ErrorResponse("Account with id " + accountId + " does not exist.");

    }*/

    private AccountJson toAccountJson(Account account) {
        AccountJson accountJson = new AccountJson();
        accountJson.setFirstName(account.getFirstName());
        accountJson.setLastName(account.getLastname());
        accountJson.setAccountId(account.getId());
        return accountJson;
    }

}
