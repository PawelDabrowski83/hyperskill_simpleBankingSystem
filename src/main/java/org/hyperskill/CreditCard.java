package org.hyperskill;


import java.util.Objects;
import java.util.regex.Pattern;

import static org.hyperskill.BankUtils.createRandomNumbers;

public class CreditCard {
    public static final CreditCard NULL_CARD = new CreditCard(null, null);

    protected static final String BIN_PREFIX = "400000";
    protected static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    protected static final int PIN_NUMBER_LENGTH = 4;
    protected static Pattern CREDIT_CARD_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");
    protected static Pattern PIN_NUMBER_FORMULA = Pattern.compile("^\\d{4}$");

    private final String creditCardNumber;
    private final String pin;
    private int balance;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    protected CreditCard(String creditCardNumber, String pin) {
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
        this.balance = 0;
    }

    protected CreditCard(String creditCardNumber, String pin, int balance){
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public static CreditCard createCreditCard(){
        return new CreditCard(createCreditCardNumber(), createPinNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(creditCardNumber, that.creditCardNumber) &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber, pin);
    }

    protected static String createCreditCardNumber(){
        String numberWithoutLuhn = BIN_PREFIX +
                createRandomNumbers(CREDIT_CARD_NUMBER_LENGTH - BIN_PREFIX.length() - 1);
        return BankUtils.appendLuhnDigit(numberWithoutLuhn);
    }

    protected static String createPinNumber(){
        return createRandomNumbers(PIN_NUMBER_LENGTH);
    }

    public static boolean validateCreditCardNumber(String creditCardNumber){
        if (creditCardNumber == null || creditCardNumber.isBlank()){
            return false;
        }
        return CREDIT_CARD_NUMBER_FORMULA.matcher(creditCardNumber).matches();
    }

    public static boolean validatePinNumber(String pinNumber){
        if (pinNumber == null || pinNumber.isBlank()){
            return false;
        }
        return PIN_NUMBER_FORMULA.matcher(pinNumber).matches();
    }

    public boolean addIncome(int amount){
        if (amount > 0){
            balance += amount;
            return true;
        }
        return false;
    }


}
