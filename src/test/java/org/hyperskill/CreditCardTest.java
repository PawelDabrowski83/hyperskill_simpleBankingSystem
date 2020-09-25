package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @DisplayName("should validateCreditCardNumber() work")
    @ParameterizedTest
    @MethodSource("validateCreditCardNumberArgumentsProvider")
    void validateCreditCardNumber(boolean expected, String given){
        assertEquals(expected, CreditCard.validateCreditCardNumber(given));
    }
    private static Stream<Arguments> validateCreditCardNumberArgumentsProvider(){
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
    public void shouldCreateCreditCardNumberReturnValidCreditCardNumber(){
        // given
        final String requiredPrefix = CreditCard.BIN_PREFIX;
        final int requiredLength = CreditCard.CREDIT_CARD_NUMBER_LENGTH;

        // when
        String[] creditCardNumbers = new String[100];
        for (int i = 0; i < creditCardNumbers.length; i++){
            creditCardNumbers[i] = CreditCard.createCreditCardNumber();
        }

        // then
        for (String actualCreditCardNumber : creditCardNumbers) {
            assertEquals(requiredPrefix, actualCreditCardNumber.substring(0,6));
            assertEquals(requiredLength, actualCreditCardNumber.length());
            assertTrue(CreditCard.CREDIT_CARD_NUMBER_FORMULA.matcher(actualCreditCardNumber).matches());
        }
    }

    @Test
    public void shouldCreatePinNumberReturnValidPinNumber(){
        // given
        final int requiredLength = CreditCard.PIN_NUMBER_LENGTH;

        // when
        String[] pinNumbers = new String[100];
        for (int i = 0; i < pinNumbers.length; i++){
            pinNumbers[i] = CreditCard.createPinNumber();
        }

        // then
        for (String actualPinNumber : pinNumbers){
            assertEquals(CreditCard.PIN_NUMBER_LENGTH, actualPinNumber.length());
            assertTrue(CreditCard.PIN_NUMBER_FORMULA.matcher(actualPinNumber).matches());
        }
    }

    @DisplayName("should validatePinNumber() work with valid input")
    @ParameterizedTest
    @ValueSource(strings = {"1234", "0000", "9999", "7893", "1463", "1000", "4081"})
    void validatePinNumberIsTrue(String given){
        assertTrue(CreditCard.validatePinNumber(given));
    }

    @DisplayName("should validatePinNumber() returns false with invalid input")
    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "123", "00", "12345", "kot", "test", "74289423", "1234;", "-100"})
    void validatePinNumberIsFalse(String given){
        assertFalse(CreditCard.validatePinNumber(given));
    }

    @Test
    public void shouldValidatePinNumberGivenNullReturnFalse(){
        // when
        String given = null;

        // then
        assertFalse(CreditCard.validatePinNumber(null));
    }


}
