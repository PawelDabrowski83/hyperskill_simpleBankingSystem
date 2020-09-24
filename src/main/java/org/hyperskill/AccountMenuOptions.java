package org.hyperskill;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AccountMenuOptions {

    DEFAULT(0), BALANCE(1), LOGOUT(2), EXIT(3);

    private final int value;
    private static final Map<Integer, AccountMenuOptions> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(AccountMenuOptions::getValue, Function.identity()));

    AccountMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccountMenuOptions getByValue(int value){
        return lookUp.getOrDefault(value, AccountMenuOptions.DEFAULT);
    }
}
