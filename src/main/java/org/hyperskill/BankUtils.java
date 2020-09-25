package org.hyperskill;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class BankUtils {
    public static final Pattern ONLY_DIGITS = Pattern.compile("\\d*");

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

    public static String appendLuhnDigit(String number){
        if (number == null || number.isBlank() || !ONLY_DIGITS.matcher(number).matches()){
            return "";
        }
        int[] digits = new int[number.length()];
        for (int i = 0; i < digits.length; i++){
            int currentDigit = -1;
            try {
                currentDigit = Integer.parseInt(String.valueOf(number.charAt(i)));
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
            digits[i] = currentDigit;
        }
        int[] luhnDigits = digits.clone();
        int checkSum = 0;
        for (int i = 0; i < luhnDigits.length; i += 2){
            int doubled = luhnDigits[i] * 2;
            if (doubled > 9){
                doubled -= 9;
            }
            luhnDigits[i] = doubled;
        }
        for (int digit : luhnDigits){
            checkSum += digit;
        }
        int controlDigit = 10 - (checkSum % 10);
        if (controlDigit == 10){
            controlDigit = 0;
        }
        return number + controlDigit;
    }

    /**
     * check if last digit of given string is valid Luhn control number
     * @param number number as string
     * @return true if number is valid
     */
    public static boolean checkLuhnNumber(String number){
        if (number == null || number.isBlank() || !ONLY_DIGITS.matcher(number).matches()){
            return false;
        }
        int expectedNumber = BankUtils.convertToInt(number.charAt(number.length() - 1));
        int[] digits = BankUtils.convertToIntArray(number.substring(0, number.length() - 1));
        int sum = 0;
        for (int i = 0; i < digits.length; i++){
            int currentDigit = digits[i];
            if (i % 2 == 0){
                currentDigit *= 2;
                if (currentDigit > 9){
                    currentDigit -= 9;
                }
            }
            sum += currentDigit;
        }
        return (sum + expectedNumber) % 10 == 0;
    }

    protected static int[] convertToIntArray(String number){
        if (number == null || number.isBlank() || !ONLY_DIGITS.matcher(number).matches()){
            return new int[0];
        }
        int[] digits = new int[number.length()];
        for (int i = 0; i < digits.length; i++){
            digits[i] = convertToInt(number.charAt(i));
        }
        return digits;
    }

    protected static int convertToInt(char c){
        int number = -1;
        try {
            number = Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return number;
    }
}
