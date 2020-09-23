package org.hyperskill;

public class Account {
    protected static final String BIN_PREFIX = "400000";
    protected static final int ACCOUNT_NUMBER_LENGTH = 16;

    private final String accountNumber;
    private String pin;

    private Account(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

//    public Account createAccount(){
//
//    }
}
