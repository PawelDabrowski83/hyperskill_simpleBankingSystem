package org.hyperskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
