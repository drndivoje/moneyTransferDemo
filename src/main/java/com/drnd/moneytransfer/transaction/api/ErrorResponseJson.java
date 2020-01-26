package com.drnd.moneytransfer.transaction.api;

public class ErrorResponseJson {
    private final String message;

    public ErrorResponseJson(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
