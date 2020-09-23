package org.hyperskill;

import java.util.concurrent.ThreadLocalRandom;

public class BankUtils {

    public static int getRandomDigit(){
        return ThreadLocalRandom.current().nextInt(10);
    }

    public static String createRandomNumbers(int length){
        if (length < 1){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        while (builder.length() < length){
            builder.append(getRandomDigit());
        }
        return builder.toString();
    }
}
