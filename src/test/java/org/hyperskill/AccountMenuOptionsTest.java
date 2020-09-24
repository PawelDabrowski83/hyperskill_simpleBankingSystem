package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hyperskill.AccountMenuOptions.*;

public class AccountMenuOptionsTest {

    @DisplayName("should enum AccountMenuOptions.getByValue() work")
    @ParameterizedTest
    @MethodSource("getByValueArgumentsProvider")
    void getById(AccountMenuOptions expected, int given){
        assertEquals(expected, AccountMenuOptions.getByValue(given));
    }
    private static Stream<Arguments> getByValueArgumentsProvider(){
        return Stream.of(
                Arguments.of(BALANCE, 1),
                Arguments.of(LOGOUT, 2),
                Arguments.of(EXIT, 3)
        );
    }

    @DisplayName("should getByValue() given invalid values returns DEFAULT")
    @ParameterizedTest
    @ValueSource(ints = {0, -3, Integer.MAX_VALUE, Integer.MIN_VALUE, 3_000_000, 33})
    void getByValueInvalidArgumentsReturnDefault(int given){
        assertEquals(DEFAULT, AccountMenuOptions.getByValue(given));
    }
}
