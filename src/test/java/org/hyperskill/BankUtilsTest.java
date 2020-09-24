package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

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
}
