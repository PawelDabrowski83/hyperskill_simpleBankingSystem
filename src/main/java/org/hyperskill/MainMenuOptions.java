package org.hyperskill;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MainMenuOptions {
    DEFAULT(0), CREATE_ACCOUNT(1), LOGIN(2), EXIT(3);

    private final int value;
    private static final Map<Integer, MainMenuOptions> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(MainMenuOptions::getValue, Function.identity()));

    MainMenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MainMenuOptions getByValue(int value){
        return lookUp.getOrDefault(value, MainMenuOptions.DEFAULT);
    }
}
