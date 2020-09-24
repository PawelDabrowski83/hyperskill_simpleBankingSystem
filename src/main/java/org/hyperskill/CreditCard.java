package org.hyperskill;


import java.util.regex.Pattern;

public class CreditCard {

    protected static final String BIN_PREFIX = "400000";
    protected static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    protected static final int PIN_NUMBER_LENGTH = 4;
    protected static Pattern CREDIT_CARD_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");
    protected static Pattern PIN_NUMBER_FORMULA = Pattern.compile("^\\d{4}$");

    private final String creditCardNumber;
    private final String pin;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    private CreditCard(String creditCardNumber, String pin) {
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
    }

    public static CreditCard createCreditCard(){
        return new CreditCard(createCreditCardNumber(), createPinNumber());
    }

    protected static String createCreditCardNumber(){
        return BIN_PREFIX + BankUtils.createRandomNumbers(CREDIT_CARD_NUMBER_LENGTH - BIN_PREFIX.length());
    }

    protected static String createPinNumber(){
        return BankUtils.createRandomNumbers(PIN_NUMBER_LENGTH);
    }

    protected static boolean validateCreditCardNumber(String creditCardNumber){
        if (creditCardNumber == null || creditCardNumber.isBlank()){
            return false;
        }
        return CREDIT_CARD_NUMBER_FORMULA.matcher(creditCardNumber).matches();
    }

    protected static boolean validatePinNumber(String pinNumber){
        if (pinNumber == null || pinNumber.isBlank()){
            return false;
        }
        return PIN_NUMBER_FORMULA.matcher(pinNumber).matches();
    }
}
