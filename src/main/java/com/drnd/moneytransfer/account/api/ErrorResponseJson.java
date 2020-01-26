package com.drnd.moneytransfer.account.api;

public class ErrorResponseJson {
    private final String message;

    public ErrorResponseJson(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
