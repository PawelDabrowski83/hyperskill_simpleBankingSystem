package org.hyperskill.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hyperskill.menu.AccountMenuOptions.*;

public class AccountMenuOptionsTest {

    @DisplayName("should enum AccountMenuOptions.getByValue() work")
    @ParameterizedTest
    @MethodSource("getByValueArgumentsProvider")
    void getById(AccountMenuOptions expected, String given){
        assertEquals(expected, AccountMenuOptions.getByValue(given));
    }
    private static Stream<Arguments> getByValueArgumentsProvider(){
        return Stream.of(
                Arguments.of(BALANCE, "1"),
                Arguments.of(ADD_INCOME, "2"),
                Arguments.of(EXIT, "0")
        );
    }

    @DisplayName("should getByValue() given invalid values returns DEFAULT")
    @ParameterizedTest
    @ValueSource(strings = {"6", "-3", "-9_000_000_000", "0000000000;", "3_000_000", "33"})
    void getByValueInvalidArgumentsReturnDefault(String given){
        assertEquals(DEFAULT, AccountMenuOptions.getByValue(given));
    }
}
