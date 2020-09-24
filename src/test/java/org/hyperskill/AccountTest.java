package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    @DisplayName("should validateAccountNumber() work")
    @ParameterizedTest
    @MethodSource("validateAccountNumberArgumentsProvider")
    void validateAccountNumber(boolean expected, String given){
        assertEquals(expected, Account.validateAccountNumber(given));
    }
    private static Stream<Arguments> validateAccountNumberArgumentsProvider(){
        return Stream.of(
                Arguments.of(false, ""),
                Arguments.of(false, "15"),
                Arguments.of(false, "kot"),
                Arguments.of(false, "4615616"),
                Arguments.of(false, "400000"),
                Arguments.of(false, "12346d48645646646"),
                Arguments.of(true, "4000004000004000"),
                Arguments.of(true, "4000004564646446"),
                Arguments.of(false, "1156564641233333"),
                Arguments.of(false, "40000045646487996"),
                Arguments.of(false, "4000554646464466")
        );
    }

    @Test
    public void shouldCreateAccountNumberReturnValidAccountNumber(){
        // given
        final String requiredPrefix = Account.BIN_PREFIX;
        final int requiredLength = Account.ACCOUNT_NUMBER_LENGTH;

        // when
        String[] creditCardNumbers = new String[100];
        int i = 0;
        while (i < creditCardNumbers.length){
            creditCardNumbers[i] = Account.createAccountNumber();
            i++;
        }

        // then
        for (String actualCreditCardNumber : creditCardNumbers) {
            assertEquals(requiredPrefix, actualCreditCardNumber.substring(0,6));
            assertEquals(requiredLength, actualCreditCardNumber.length());
            assertTrue(Account.ACCOUNT_NUMBER_FORMULA.matcher(actualCreditCardNumber).matches());
        }


    }
}
