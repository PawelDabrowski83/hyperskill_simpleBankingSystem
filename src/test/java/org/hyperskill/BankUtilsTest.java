package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BankUtilsTest {

    @Test
    public void shouldGetRandomDigitReturnIntBetween0And9(){
        final int repetitions = 10_000;
        for (int i = 0; i < repetitions; i++){
            int actual = BankUtils.getRandomDigit();
            assertTrue(actual > -1);
            assertTrue(actual < 10);
        }
    }

    @DisplayName("should createRandomNumbers() return string with random digits for normal parameters")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 11, 20, 100_000})
    void createRandomNumbers(int length){
        assertEquals(length, BankUtils.createRandomNumbers(length).length());
    }

    @Test
    public void shouldCreateRandomNumbersThrowIllegalArgumentExceptionGivenLengthMoreThan100_000(){
        // given
        final int[] length = {120_000, 1_000_000, Integer.MAX_VALUE};

        // then
        for (final int targetLength : length) {
            assertThrows(IllegalArgumentException.class, () -> BankUtils.createRandomNumbers(targetLength));
        }
    }

    @Test
    public void shouldCreateRandomNumbersThrowIllegalArgumentExceptionGivenLengthLessThan1(){
        // given
        final int[] length = {0, -13, -2000, -3_000_000, Integer.MIN_VALUE};

        // then
        for (final int targetLength : length) {
            assertThrows(IllegalArgumentException.class, () -> BankUtils.createRandomNumbers(targetLength));
        }
    }

    @DisplayName("should appendLuhnDigit return number with added control digit from Luhn algorithm")
    @ParameterizedTest
    @MethodSource("appendLuhnDigitArgumentsProvider")
    void appendLuhnDigit(String expected, String given){
        assertEquals(expected, BankUtils.appendLuhnDigit(given));
    }
    private static Stream<Arguments> appendLuhnDigitArgumentsProvider(){
        return Stream.of(
                Arguments.of("4000008449433403", "400000844943340"),
                Arguments.of("10000000000008", "1000000000000"),
                Arguments.of("1008", "100"),
                Arguments.of("10000016", "1000001"),
                Arguments.of("", ""),
                Arguments.of("12346", "1234")
        );
    }

    @DisplayName("should checkLuhnNumber() verify if number was given correctly")
    @ParameterizedTest
    @MethodSource("checkLuhnNumberArgumentsProvider")
    void checkLuhnNumber(boolean expected, String number){
        assertEquals(expected, BankUtils.checkLuhnNumber(number));
    }
    private static Stream<Arguments> checkLuhnNumberArgumentsProvider(){
        return Stream.of(
                Arguments.of(true, "10000016"),
                Arguments.of(true, "4000008449433403"),
                Arguments.of(true, "1008"),
                Arguments.of(true, "12346"),
                Arguments.of(false, ""),
                Arguments.of(false, "12345"),
                Arguments.of(false, "1001"),
                Arguments.of(false, "3000008449433403")
        );
    }

    @DisplayName("should convertToIntArray() work")
    @ParameterizedTest
    @MethodSource("convertToIntArrayArgumentsProvider")
    void convertToIntArray(int[] expected, String given){
        assertArrayEquals(expected, BankUtils.convertToIntArray(given));
    }
    private static Stream<Arguments> convertToIntArrayArgumentsProvider(){
        return Stream.of(
                Arguments.of(new int[]{1, 0, 0, 3, 7}, "10037"),
                Arguments.of(new int[0], ""),
                Arguments.of(new int[]{9, 9, 8, 8, 0, 3, 1, 0, 0, 1, 4}, "99880310014"),
                Arguments.of(new int[0], "kot"),
                Arguments.of(new int[0], "-17")
        );
    }


}
