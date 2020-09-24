package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hyperskill.MainMenuOptions.*;

public class MainMenuOptionsTest {

    @DisplayName("should enum MainMenuOptions.getByValue() work")
    @ParameterizedTest
    @MethodSource("getByValueArgumentsProvider")
    void getById(MainMenuOptions expected, int given){
        assertEquals(expected, MainMenuOptions.getByValue(given));
    }
    private static Stream<Arguments> getByValueArgumentsProvider(){
        return Stream.of(
                Arguments.of(CREATE_ACCOUNT, 1),
                Arguments.of(LOGIN, 2),
                Arguments.of(EXIT, 3)
        );
    }

    @DisplayName("should getByValue() given invalid values returns DEFAULT")
    @ParameterizedTest
    @ValueSource(ints = {0, -3, Integer.MAX_VALUE, Integer.MIN_VALUE, 3_000_000, 33})
    void getByValueInvalidArgumentsReturnDefault(int given){
        assertEquals(DEFAULT, MainMenuOptions.getByValue(given));
    }
}
