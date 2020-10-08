package org.hyperskill.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AccountMenuOptions {

    DEFAULT("-1"),
    BALANCE("1"),
    ADD_INCOME("2"),
    DO_TRANSFER("3"),
    CLOSE_ACCOUNT("4"),
    LOGOUT("5"),
    EXIT("0");

    private final String value;
    private static final Map<String, AccountMenuOptions> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(AccountMenuOptions::getValue, Function.identity()));

    AccountMenuOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AccountMenuOptions getByValue(String value){
        return lookUp.getOrDefault(value, AccountMenuOptions.DEFAULT);
    }
}
