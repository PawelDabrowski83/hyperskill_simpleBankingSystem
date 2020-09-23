package org.hyperskill;

import java.util.concurrent.ThreadLocalRandom;

public class BankUtils {

    public static int getRandomDigit(){
        return ThreadLocalRandom.current().nextInt(10);
    }

    public static String createRandomNumbers(int length) throws IllegalArgumentException{
        if (length < 1 || length > 100_000){
            throw new IllegalArgumentException("Length should be between 1 and 100_000");
        }
        StringBuilder builder = new StringBuilder();
        while (builder.length() < length){
            builder.append(getRandomDigit());
        }
        return builder.toString();
    }
}
