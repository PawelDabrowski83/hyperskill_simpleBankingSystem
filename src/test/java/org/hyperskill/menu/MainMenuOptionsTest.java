package org.hyperskill.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hyperskill.menu.MainMenuOptions.*;

public class MainMenuOptionsTest {

    @DisplayName("should enum MainMenuOptions.getByValue() work")
    @ParameterizedTest
    @MethodSource("getByValueArgumentsProvider")
    void getById(MainMenuOptions expected, String given){
        assertEquals(expected, MainMenuOptions.getByValue(given));
    }
    private static Stream<Arguments> getByValueArgumentsProvider(){
        return Stream.of(
                Arguments.of(CREATE_CREDIT_CARD, "1"),
                Arguments.of(LOGIN, "2"),
                Arguments.of(EXIT, "0")
        );
    }

    @DisplayName("should getByValue() given invalid values returns DEFAULT")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "3", "-3", "-3_000_000", "3_000_000", "33", "9999999999"})
    void getByValueInvalidArgumentsReturnDefault(String given){
        assertEquals(DEFAULT, MainMenuOptions.getByValue(given));
    }
}
