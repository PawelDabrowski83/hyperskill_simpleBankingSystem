package org.hyperskill.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MainMenuOptions {
    DEFAULT("0"), CREATE_ACCOUNT("1"), LOGIN("2"), EXIT("3");

    private final String value;
    private static final Map<String, MainMenuOptions> lookUp = Arrays.stream(values())
            .collect(Collectors.toMap(MainMenuOptions::getValue, Function.identity()));

    MainMenuOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MainMenuOptions getByValue(String value){
        return lookUp.getOrDefault(value, MainMenuOptions.DEFAULT);
    }
}
