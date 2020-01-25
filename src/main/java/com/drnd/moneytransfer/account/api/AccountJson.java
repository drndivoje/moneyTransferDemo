package com.drnd.moneytransfer.account.api;

public class AccountJson {
    private String firstName;
    private String lastName;

    public AccountJson() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
